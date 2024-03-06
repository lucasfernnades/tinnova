package com.tinnova.technical_assessment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {

    private String id;

    private boolean success;

    private String message;

    private Object data;
}
