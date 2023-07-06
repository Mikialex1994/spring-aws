package com.example.pokemoncopy2.service;

import com.example.pokemoncopy2.dto.PokemonDto;
import com.example.pokemoncopy2.entity.PokemonResponse;

public interface PokemonService {

    PokemonDto createPokemon(PokemonDto pokemonDto);

    PokemonResponse getAllPokemon(int pageNo, int pageSize);

    PokemonDto getPokemonById(int pokemonId);

    PokemonDto updatePokemon(int pokemonId, PokemonDto pokemonDto);

    void deletePokemon(int pokemonId);
}
