package com.coolstories.projectstories.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class PSAuthException extends RuntimeException { 
    
    public PSAuthException(String message) {
        super (message);
    }
}
