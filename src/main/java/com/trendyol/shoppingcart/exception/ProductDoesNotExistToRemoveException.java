package com.trendyol.shoppingcart.exception;

public class ProductDoesNotExistToRemoveException extends RuntimeException {

    public ProductDoesNotExistToRemoveException(String title) {
        super( title + " does not contain in shopping cart");
    }
}
