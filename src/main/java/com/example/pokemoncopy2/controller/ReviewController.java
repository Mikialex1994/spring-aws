package com.example.pokemoncopy2.controller;

import com.example.pokemoncopy2.dto.ReviewDto;
import com.example.pokemoncopy2.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("pokemon/{pokemonId}/reviews")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto, @PathVariable(value = "pokemonId") int pokemonId){

        return new ResponseEntity<>(reviewService.createReview(reviewDto,pokemonId), HttpStatus.CREATED);
    }

    @GetMapping("pokemon/{pokemonId}/reviews/{id}")
    public ResponseEntity<ReviewDto> getReviewByPokemonId(@PathVariable(value = "pokemonId") int pokemonId, @PathVariable(value = "id") int reviewId){

        return new ResponseEntity<>(reviewService.getReviewByPokemonId(pokemonId,reviewId),HttpStatus.OK);
    }

    @GetMapping("pokemon/{pokemonId}/reviews")
    public List<ReviewDto> getAllByPokemonId(@PathVariable(value = "pokemonId") int pokemonId){

        return reviewService.getAllByPokemonId(pokemonId);
    }

    @PutMapping("pokemon/{pokemonId}/reviews/{id}")
    public ResponseEntity<ReviewDto> updateReview(@RequestBody ReviewDto reviewDto, @PathVariable(value = "pokemonId") int pokemonId, @PathVariable(value = "id") int reviewId){

        return new ResponseEntity<>(reviewService.updateReview(reviewDto,pokemonId,reviewId),HttpStatus.OK);
    }

    @DeleteMapping("pokemon/{pokemonId}/reviews/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable(value = "pokemonId") int pokemonId, @PathVariable("id") int reviewId){

        reviewService.deleteReview(reviewId,pokemonId);

        return new ResponseEntity<>("The Review with associate id DELETED!!!",HttpStatus.OK);
    }

}
