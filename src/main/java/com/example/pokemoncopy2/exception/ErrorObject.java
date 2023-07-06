package com.example.pokemoncopy2.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorObject {

    private int statusCode;
    private String message;
    private Date timeStamp;

}
