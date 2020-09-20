package com.trendyol.shoppingcart.exception;

public class ProductDoesNotHaveEnoughQuantityToRemoveException extends RuntimeException {

    public ProductDoesNotHaveEnoughQuantityToRemoveException(String title) {
        super( " There is no enough amount in shopping cart for this product : " + title);
    }
}
