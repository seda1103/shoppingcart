package com.trendyol.shoppingcart.exception;

public class ProductAlreadyExistsException extends RuntimeException {

    public ProductAlreadyExistsException(String title) {
        super("Product already  defined " + title);
    }
}
