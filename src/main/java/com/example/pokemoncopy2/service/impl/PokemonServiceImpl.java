package com.example.pokemoncopy2.service.impl;

import com.example.pokemoncopy2.dto.PokemonDto;
import com.example.pokemoncopy2.entity.Pokemon;
import com.example.pokemoncopy2.entity.PokemonResponse;
import com.example.pokemoncopy2.exception.PokemonNotFoundException;
import com.example.pokemoncopy2.repository.PokemonRepository;
import com.example.pokemoncopy2.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    private PokemonRepository pokemonRepository;

    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {

        Pokemon pokemon = new Pokemon();

        pokemon.setId(pokemonDto.getId());
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        Pokemon newPokemon = pokemonRepository.save(pokemon);

        return mapToDto(newPokemon);

    }

    @Override
    public PokemonResponse getAllPokemon(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo,pageSize);

        Page<Pokemon> pokemon = pokemonRepository.findAll(pageable);

        List<Pokemon> listOfPokemon = pokemon.getContent();

        List<PokemonDto> content = listOfPokemon.stream().map(this::mapToDto).toList();

        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setContent(content);
        pokemonResponse.setPageNo(pokemon.getNumber());
        pokemonResponse.setPageSize(pokemon.getSize());
        pokemonResponse.setTotalPages(pokemon.getTotalPages());
        pokemonResponse.setTotalElements(pokemon.getTotalElements());
        pokemonResponse.setLast(pokemon.isLast());

        return pokemonResponse;
    }

    @Override
    public PokemonDto getPokemonById(int pokemonId) {

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated id not found"));

        return mapToDto(pokemon);
    }

    @Override
    public PokemonDto updatePokemon(int pokemonId, PokemonDto pokemonDto) {

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated id not found"));

        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        Pokemon newPokemon = pokemonRepository.save(pokemon);

        return mapToDto(newPokemon);
    }

    @Override
    public void deletePokemon(int pokemonId) {

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated id is not found"));

        pokemonRepository.delete(pokemon);
    }

    private PokemonDto mapToDto(Pokemon pokemon){

        PokemonDto pokemonDto = new PokemonDto();

        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());

        return pokemonDto;
    }

    private Pokemon mapToEntity(PokemonDto pokemonDto){

        Pokemon pokemon = new Pokemon();

        pokemon.setId(pokemonDto.getId());
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        return pokemon;
    }


}
