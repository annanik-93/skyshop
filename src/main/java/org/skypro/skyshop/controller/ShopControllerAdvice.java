package org.skypro.skyshop.controller;

import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.error.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice /// критерий - аннотация @ControllerAdvice
public class ShopControllerAdvice {

    /// критерий - аннотация @ExceptionHandler для NoSuchProductException
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProductException(NoSuchProductException e) {

        /// создаем объект ошибки, задаем машиночитаемый код "PRODUCT_NOT_FOUND", и передаем текст сообщения, сформированный внутри самого исключения.
        ShopError errorResponse = new ShopError("PRODUCT_NOT_FOUND", e.getMessage());

        /// критерий - корректно возвращает ResponseEntity<ShopError> с HTTP-кодом 404 (Not Found)
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }
}