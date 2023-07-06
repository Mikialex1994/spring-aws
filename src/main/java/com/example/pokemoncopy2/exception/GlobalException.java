package com.example.pokemoncopy2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(PokemonNotFoundException.class)
    private ResponseEntity<ErrorObject> handlePokemonExceptions(PokemonNotFoundException pokemonNotFoundException, WebRequest request){

        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(pokemonNotFoundException.getMessage());
        errorObject.setTimeStamp(new Date());

        return new ResponseEntity<>(errorObject,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    private ResponseEntity<ErrorObject> handleReviewException(ReviewNotFoundException reviewNotFoundException,WebRequest request){

        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(reviewNotFoundException.getMessage());
        errorObject.setTimeStamp(new Date());

        return new ResponseEntity<>(errorObject,HttpStatus.NOT_FOUND);
    }


}
