package com.example.mall.controller.Exception;

public class InvalidArgumentException extends RuntimeException {

	
	private String message;

    public static InvalidArgumentException createWith(String message) {
        return new InvalidArgumentException(message);
    }

    private InvalidArgumentException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return  message;
    }
}