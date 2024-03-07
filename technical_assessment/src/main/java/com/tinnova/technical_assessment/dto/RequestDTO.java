package com.tinnova.technical_assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {

    private String model;

    private String make;

    private Integer releaseYear;

    private String color;

    private Boolean sold;
}
