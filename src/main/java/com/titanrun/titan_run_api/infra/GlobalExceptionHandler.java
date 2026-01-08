package com.titanrun.titan_run_api.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Todo IllegalArgumentException cai aqui
    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                e.getMessage()
        );

        problemDetail.setTitle("Regra de Negócio Violada");
        problemDetail.setType(URI.create("https://titanrun.com/errors/regras-de-negocio"));
        problemDetail.setProperty("timestamp", java.time.Instant.now());

        return problemDetail;
    }
}
