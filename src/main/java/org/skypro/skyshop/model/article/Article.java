package org.skypro.skyshop.model.article;

import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final UUID id; /// шаг 2 - уникальный идентификатор
    private final String title;
    private final String text;

    public Article(UUID id, String title, String text) {
        if (id == null) {
            throw new IllegalArgumentException("ID не может быть null.");
        }
        this.id = id;
        this.title = title;
        this.text = text;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public String getSearchTerm() {
        return toString();
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String toString() {
        return title + "\n" + text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; //проверка на равенство ссылок
        if (o == null || getClass() != o.getClass()) return false; //проверка на null и класс
        Article article = (Article) o;
        return Objects.equals(title, article.title); //сравнение по имени поля
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}