package com.example.pokemoncopy2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewDto {

    private int id;
    private String title;
    private String content;
    private int stars;

}
