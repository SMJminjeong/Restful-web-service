package com.example.restfulwebservice.helloworld.User;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//HTTP Status Code
//2XX -> OK
//4XX -> Client (권한,메소드,...)
//5XX -> Server
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
