package com.example.pokemoncopy2.exception;

public class PokemonNotFoundException extends RuntimeException{

    public PokemonNotFoundException(String message){

        super(message);
    }

}
