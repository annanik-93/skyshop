package org.skypro.skyshop.service;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.Optional;


/*
- класс хранилища помечается аннотацией @Service,
- использует private final мапы и приватный метод для генерации данных через UUID.randomUUID()
- добавляем метод объединения коллекций для Шага 5 */

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
        initStorage(); /// вызов приватного метода заполнения данными
    }

    /// критерий - заполнение тестовыми данными с уникальными UUID через приватный метод
    private void initStorage() {
        /// из предыдущего проета (старого метода main)
        // добавление продуктов
        UUID id1 = UUID.randomUUID();
        products.put(id1, new SimpleProduct(id1, "Смартфон", 45000));

        UUID id2 = UUID.randomUUID();
        products.put(id2, new DiscountedProduct(id2, "Ноутбук", 100000, 20));

        UUID id3 = UUID.randomUUID();
        products.put(id3, new FixPriceProduct(id3, "Наушники"));

        UUID id4 = UUID.randomUUID();
        products.put(id4, new SimpleProduct(id4, "Часы", 20000));

        UUID id5 = UUID.randomUUID();
        products.put(id5, new SimpleProduct(id5, "Мышь", 3500));

        UUID id6 = UUID.randomUUID();
        products.put(id6, new SimpleProduct(id6, "Клавиатура", 6000));

        /// из предыдущего проета (старого метода main)
        // добавление статей
        UUID artId1 = UUID.randomUUID();
        articles.put(artId1, new Article(
                artId1,
                "Как выбрать Смартфон в 2026 году",
                "При выборе смартфона обращайте внимание на процессор."
        ));

        UUID artId2 = UUID.randomUUID();
        articles.put(artId2, new Article(
                artId2,
                "Обзор игрового Ноутбука",
                "Этот мощный ноутбук тянет все современные игры на ультра-настройках."
        ));
    }

    /// возвращает коллекцию всех продуктов
    public Collection<Product> getAllProducts() {
        return products.values();
    }

    /// возвращает коллекцию всех статей
    public Collection<Article> getAllArticles() {
        return articles.values();
    }

    /// критерий (Шаг 2) - получение продукта по id через Optional
    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }

    /// метод для сервиса поиска - объединяет продукты и статьи в одну коллекцию
    public Collection<Searchable> getAllSearchables() {
        List<Searchable> allItems = new ArrayList<>();
        allItems.addAll(products.values());
        allItems.addAll(articles.values());
        return allItems;
    }
}