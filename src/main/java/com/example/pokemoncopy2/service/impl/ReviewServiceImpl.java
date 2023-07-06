package com.example.pokemoncopy2.service.impl;

import com.example.pokemoncopy2.dto.ReviewDto;
import com.example.pokemoncopy2.entity.Pokemon;
import com.example.pokemoncopy2.entity.Review;
import com.example.pokemoncopy2.exception.PokemonNotFoundException;
import com.example.pokemoncopy2.exception.ReviewNotFoundException;
import com.example.pokemoncopy2.repository.PokemonRepository;
import com.example.pokemoncopy2.repository.ReviewRepository;
import com.example.pokemoncopy2.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private PokemonRepository pokemonRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository) {
        this.reviewRepository = reviewRepository;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public ReviewDto createReview(ReviewDto reviewDto, int pokemonId) {

        Review review = mapTOEntity(reviewDto);

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated id not found"));

        review.setPokemon(pokemon);

        Review newReview = reviewRepository.save(review);

        return mapToDto(newReview);
    }

    @Override
    public ReviewDto getReviewByPokemonId(int pokemonId, int reviewId) {

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated id not found"));

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException("Review with associated id not found"));

        if (review.getPokemon().getId() != pokemon.getId()){

            throw new ReviewNotFoundException("Review with associated id not found");
        }

        return mapToDto(review);
    }

    @Override
    public List<ReviewDto> getAllByPokemonId(int pokemonId) {

        List<Review> review = reviewRepository.findAllByPokemonId(pokemonId);

        return review.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ReviewDto updateReview(ReviewDto reviewDto, int pokemonId, int reviewId) {

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated id not found"));

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException("Review with associated id not found"));

        if (review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("Review with associated id not found");
        }

        review.setTitle(reviewDto.getTitle());
        review.setStars(reviewDto.getStars());
        review.setContent(reviewDto.getContent());

        Review newReview = reviewRepository.save(review);

        return mapToDto(newReview);

    }

    @Override
    public void deleteReview(int reviewId, int pokemonId) {

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated id not found"));

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException("Review with associated id not found"));

        if (review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("Review with associated id not found");
        }

        reviewRepository.delete(review);
    }

    private ReviewDto mapToDto(Review review){

        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());

        return reviewDto;
    }

    private Review mapTOEntity(ReviewDto reviewDto){

        Review review = new Review();

        review.setId(reviewDto.getId());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());

        return review;
    }

}
