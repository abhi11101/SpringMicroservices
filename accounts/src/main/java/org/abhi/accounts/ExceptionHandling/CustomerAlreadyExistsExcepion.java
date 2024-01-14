package org.abhi.accounts.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistsExcepion extends RuntimeException{

    public CustomerAlreadyExistsExcepion(String message){
        super(message);
    }
}
