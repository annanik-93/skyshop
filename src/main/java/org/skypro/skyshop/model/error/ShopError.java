package org.skypro.skyshop.model.error;

/// модель для возврата ошибки в формате JSON
public class ShopError {
    /// критерий - два поля code и message, оба объявлены как final (сеттеры отсутствуют)
    private final String code;
    private final String message;

    public ShopError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}