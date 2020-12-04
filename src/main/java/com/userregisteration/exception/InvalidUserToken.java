package com.userregisteration.exception;

public class InvalidUserToken extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InvalidUserToken() {
        super();
    }

    public InvalidUserToken(String exMsg) {
        super(exMsg);
    }
}
