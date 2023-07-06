package com.example.pokemoncopy2.service;

import com.example.pokemoncopy2.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    ReviewDto createReview(ReviewDto reviewDto, int pokemonId);

    ReviewDto getReviewByPokemonId(int pokemonId, int reviewId);

    List<ReviewDto> getAllByPokemonId(int pokemonId);

    ReviewDto updateReview(ReviewDto reviewDto, int pokemonId, int reviewId);

    void deleteReview(int reviewId, int pokemonId);
}
