package com.trendyol.shoppingcart.exception;

public class ObjectAlreadyUpdatedException extends RuntimeException {

    public ObjectAlreadyUpdatedException() {
        super("Object is already updated");
    }
}
