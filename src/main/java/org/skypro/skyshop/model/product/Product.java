package org.skypro.skyshop.model.product;
import org.skypro.skyshop.model.search.Searchable;
import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final UUID id; /// шаг 2 - уникальный идентификатор
    private final String name;

    public Product(UUID id, String name) {
        if (id == null) {
            throw new IllegalArgumentException("ID не может быть null.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым.");
        }
        this.id = id;
        this.name = name;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSearchTerm() {
        return name;
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; //проверка на равенство ссылок
        if (o == null || getClass() != o.getClass()) return false; //проверка на null и класс
        Product product = (Product) o;
        return Objects.equals(name, product.name); //сравнение по имени
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
