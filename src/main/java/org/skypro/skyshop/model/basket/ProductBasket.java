package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*
Создаем класс в пакете org.skypro.skyshop.model.basket.
Помечаем его @Component и @SessionScope.
Мапу инициализируем как HashMap, а для добавления используем computeIfAbsent, чтобы правильно увеличивать кол-во
 */

@Component
@SessionScope /// критерий - бин привязан к сессии пользователя
public class ProductBasket {
    /// критерий - поле private final, инициализировано HashMap
    private final Map<UUID, Integer> basket = new HashMap<>();

    /// критерий - добавление с увеличением количества, использован computeIfAbsent
    public void addProduct(UUID id) {
        if (id != null) {
            basket.compute(id, (key, count) -> (count == null) ? 1 : count + 1);
        }
    }

    /// критерий - возвращает неизменяемую мапу через Collections.unmodifiableMap()
    public Map<UUID, Integer> getBasketItems() {
        return Collections.unmodifiableMap(basket);
    }
}

// метод Map.compute под капотом использует ту же атомарную логику ветвления, что и computeIfAbsent, но более лаконично увеличивает счетчик Integer для существующих ключей)