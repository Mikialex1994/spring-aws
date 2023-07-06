package com.example.pokemoncopy2.repository;

import com.example.pokemoncopy2.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon,Integer> {
}
