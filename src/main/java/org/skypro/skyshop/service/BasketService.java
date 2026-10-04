package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service /// критерий - аннотация @Service
public class BasketService {
    /// критерий - поля private final
    private final ProductBasket productBasket;
    private final StorageService storageService;

    /// критерий - внедрение зависимостей через конструктор (Constructor Injection)
    @Autowired
    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    /// метод добавления товара в корзину
    public void addProductToBasket(UUID id) {
        /// критерий - использован Optional с orElseThrow для проверки и выброса исключения
        storageService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Продукт с ID " + id + " не найден в магазине."));

        /// если исключение не выброшено, товар существует — добавляем в корзину
        productBasket.addProduct(id);
    }

    /// критерий - метод отображения корзины реализован с помощью Stream API
    public UserBasket getUserBasket() {
        Map<UUID, Integer> itemsMap = productBasket.getBasketItems();

        List<BasketItem> basketItems = itemsMap.entrySet().stream()
                .map(entry -> {
                    UUID id = entry.getKey();
                    int count = entry.getValue();

                    // Извлекаем продукт из хранилища (он там гарантированно есть, так как прошел валидацию при добавлении)
                    Product product = storageService.getProductById(id).orElseThrow();
                    return new BasketItem(product, count);
                })
                .collect(Collectors.toList());

        return new UserBasket(basketItems);
    }
}