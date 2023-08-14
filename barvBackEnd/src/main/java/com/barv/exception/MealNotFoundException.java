package com.barv.exception;

public class MealNotFoundException extends Exception {
    public MealNotFoundException() {
        super();
    }

    public MealNotFoundException(String message) {
        super(message);
    }

    public MealNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MealNotFoundException(Throwable cause) {
        super(cause);
    }

    protected MealNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
