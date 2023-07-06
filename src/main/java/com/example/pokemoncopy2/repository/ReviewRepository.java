package com.example.pokemoncopy2.repository;

import com.example.pokemoncopy2.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
    List<Review> findAllByPokemonId(int pokemonId);
}
