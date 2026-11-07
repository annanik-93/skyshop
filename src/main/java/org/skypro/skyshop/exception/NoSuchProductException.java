package org.skypro.skyshop.exception;

/// критерий - унаследовано от RuntimeException и передает значимый текст ошибки в super()
public class NoSuchProductException extends RuntimeException {

    public NoSuchProductException(String message) {
        super(message);
    }
}