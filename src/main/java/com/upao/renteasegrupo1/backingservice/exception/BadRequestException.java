package com.upao.renteasegrupo1.backingservice.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException() {
    }
}
