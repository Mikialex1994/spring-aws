package com.example.pokemoncopy2.controller;

import com.example.pokemoncopy2.dto.PokemonDto;
import com.example.pokemoncopy2.entity.PokemonResponse;
import com.example.pokemoncopy2.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PokemonController {

    private PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @PostMapping("pokemon/create")
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto){

        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto), HttpStatus.CREATED);
    }

    @GetMapping("pokemon")
    public ResponseEntity<PokemonResponse> getAllPokemon(
            @RequestParam(value = "pageNo" ,defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize
    ){

        return new ResponseEntity<>(pokemonService.getAllPokemon(pageNo,pageSize),HttpStatus.OK);
    }

    @GetMapping("pokemon/{id}")
    public ResponseEntity<PokemonDto> getPokemonById(@PathVariable(value = "id") int pokemonId){

        return new ResponseEntity<>(pokemonService.getPokemonById(pokemonId),HttpStatus.OK);

    }

    @PutMapping("pokemon/{id}/update")
    public ResponseEntity<PokemonDto> updatePokemon(@PathVariable(value = "id") int pokemonId, @RequestBody PokemonDto pokemonDto){

        return new ResponseEntity<>(pokemonService.updatePokemon(pokemonId,pokemonDto),HttpStatus.OK);
    }

    @DeleteMapping("pokemon/{id}")
    public ResponseEntity<String> deletePokemon(@PathVariable(value = "id") int pokemonId){

        pokemonService.deletePokemon(pokemonId);

      return new ResponseEntity<>("The pokemon with associated id has been deleted",HttpStatus.OK);

    }


}
