package com.example.WebApp.domain.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class resourceNotFoundHandler extends Exception{
    private static final long serialVersionUID = 1L;

    public resourceNotFoundHandler(String message){
        super(message);
    }
}
