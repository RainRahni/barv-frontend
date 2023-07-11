package com.barv.exception;

public class FoodNotFoundException extends Exception {
    public FoodNotFoundException() {
        super();
    }

    public FoodNotFoundException(String message) {
        super(message);
    }

    public FoodNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FoodNotFoundException(Throwable cause) {
        super(cause);
    }

    protected FoodNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
