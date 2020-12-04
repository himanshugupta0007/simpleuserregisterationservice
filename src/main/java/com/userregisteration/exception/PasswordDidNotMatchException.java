package com.userregisteration.exception;

public class PasswordDidNotMatchException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public PasswordDidNotMatchException() {
        super();
    }

    public PasswordDidNotMatchException(String exceptionMessage) {
        super(exceptionMessage);
    }

}
