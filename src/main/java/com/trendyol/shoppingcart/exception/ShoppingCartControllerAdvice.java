package com.trendyol.shoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ShoppingCartControllerAdvice {

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<Object> productAlreadyExistsHandler() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", "PRODUCT_ALREADY_EXITS");
        body.put("description", "A product with the given title already exists");

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<Object> categoryAlreadyExistsHandler() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", "CATEGORY_ALREADY_EXITS");
        body.put("description", "A category with the given title already exists");

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ObjectAlreadyUpdatedException.class)
    public ResponseEntity<Object> objectAlreadyUpdatedHandler() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", "OBJECT_ALREADY_UPDATED");
        body.put("description", "Object is already updated");

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProductDoesNotExistToRemoveException.class)
    public ResponseEntity<Object> productDoesNotExistToRemoveHandler() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", "PRODUCT_DOES_NOT_EXIST");
        body.put("description", "Product does not contain in shopping cart");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductDoesNotHaveEnoughQuantityToRemoveException.class)
    public ResponseEntity<Object> productDoesNotHaveEnoughCompanyToRemoveHandler() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", "PRODUCT_DOES_NOT_HAVE_ENOUGH_AMOUNT");
        body.put("description", "There is no enough amount in shopping cart for this product");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> methodArgumentNotValidHandler(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        for(FieldError error : ex.getBindingResult().getFieldErrors()){
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for(ObjectError error : ex.getBindingResult().getGlobalErrors()){
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", "NOT_VALID_ARGUMENT");
        body.put("description", errors);


        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler ({ResourceNotFoundException.class})
    public ResponseEntity<Object> resourceNotFoundExceptionHandler(Exception ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", "RESOURCE_NOT_FOUND");
        body.put("description", "There is no resource");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
