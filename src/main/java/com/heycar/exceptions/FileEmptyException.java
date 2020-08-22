package com.heycar.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus( code = HttpStatus.NOT_FOUND )
public class FileEmptyException extends Exception {

    private static final long serialVersionUID = 1L;

    public FileEmptyException( String message ) {
        super( message );
    }

}
