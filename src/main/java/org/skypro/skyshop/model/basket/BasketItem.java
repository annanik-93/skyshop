package org.skypro.skyshop.model.basket;

/*
Создаем иммутабельные (немодифицируемые) классы без сеттеров.
Поле total вычисляется строго в конструкторе UserBasket через Stream API
*/

import org.skypro.skyshop.model.product.Product;

public class
BasketItem {
    private final Product product;
    private final int count;

    public BasketItem(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public int getCount() {
        return count;
    }
}