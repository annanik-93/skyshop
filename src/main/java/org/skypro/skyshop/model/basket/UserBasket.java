package org.skypro.skyshop.model.basket;

/*
Создаем иммутабельные (немодифицируемые) классы без сеттеров.
Поле total вычисляется строго в конструкторе UserBasket через Stream API
*/

import java.util.List;

public class UserBasket {
    private final List<BasketItem> items;
    private final int total; /// критерий - вычисляется в конструкторе

    /// критерий - конструктор принимает только List<BasketItem>
    public UserBasket(List<BasketItem> items) {
        this.items = items;
        // Критерий: вычисление total через items.stream().mapToInt(...).sum()
        this.total = items.stream()
                .mapToInt(item -> item.getProduct().getPrice() * item.getCount())
                .sum();
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public int getTotal() {
        return total;
    }
}