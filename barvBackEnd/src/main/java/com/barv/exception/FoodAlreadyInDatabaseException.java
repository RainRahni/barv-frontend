package com.barv.exception;

public class FoodAlreadyInDatabaseException extends Exception {
    public FoodAlreadyInDatabaseException() {
        super();
    }

    public FoodAlreadyInDatabaseException(String message) {
        super(message);
    }

    public FoodAlreadyInDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public FoodAlreadyInDatabaseException(Throwable cause) {
        super(cause);
    }

    protected FoodAlreadyInDatabaseException(String message, Throwable cause, boolean enableSuppression,
                                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
