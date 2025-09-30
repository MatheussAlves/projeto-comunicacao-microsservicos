package com.br.curso_udemy.product_api.config.exception;

import lombok.Data;


@Data
public class ExceptionDetails {

    private int status;
    private String message;
}
