package org.skypro.skyshop.model.search;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public interface Searchable {
    UUID getId(); /// новый метод для получения уникального идентификатора

    @JsonIgnore /// шаг 4 - исключаем из вывода в JSON
    String getSearchTerm();

    @JsonIgnore /// шаг 4 - исключаем из вывода в JSON
    String getContentType();

    String getName();

    default String getStringRepresentation() {
        return getName() + " — " + getContentType();
    }
}