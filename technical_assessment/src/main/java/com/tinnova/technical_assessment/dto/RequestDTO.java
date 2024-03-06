package com.tinnova.technical_assessment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDTO {

    private String model;

    private String make;

    private Integer releaseYear;

    private String color;

    private Boolean sold;
}
