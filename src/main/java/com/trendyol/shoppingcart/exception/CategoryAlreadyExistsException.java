package com.trendyol.shoppingcart.exception;

public class CategoryAlreadyExistsException extends RuntimeException {

    public CategoryAlreadyExistsException(String title) {
        super("Category already  defined " + title);
    }
}
