package com.afidal.usuariosservice.exceptions;

public class BadRequestException extends Exception{

    public BadRequestException(String message){
        super(message);
    }

}