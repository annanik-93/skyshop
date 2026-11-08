package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // слой тестирования изолирован Mockito Extension
class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    /// СЦЕНАРИЙ 1 - добавляем несуществующий товар в корзину приводим к выбросу исключения
    @Test
    void addProductToBasket_ShouldThrowException_WhenProductDoesNotExist() {
        UUID invalidId = UUID.randomUUID();
        /// критерий - StorageService возвращает Optional.empty()
        when(storageService.getProductById(invalidId)).thenReturn(Optional.empty());

        /// критерий - используем assertThrows для проверки выброса исключения (NoSuchProductException)
        assertThrows(NoSuchProductException.class, () -> basketService.addProductToBasket(invalidId));

        /// верифицируем, что добавления в корзину не произошло
        verify(productBasket, never()).addProduct(any());
    }

    /// СЦЕНАРИЙ 2 - добавлем существующий товара - вызывает метод addProduct у мока ProductBasket
    @Test
    void addProductToBasket_ShouldCallBasketAddProduct_WhenProductExists() {
        UUID validId = UUID.randomUUID();
        Product mockProduct = new SimpleProduct(validId, "Кроссовки", 3000);

        /// критерий - StorageService возвращает Optional.of(product)
        when(storageService.getProductById(validId)).thenReturn(Optional.of(mockProduct));

        basketService.addProductToBasket(validId);

        /// критерий - используется verify(productBasket, times(1)).addProduct(validId)
        verify(productBasket, times(1)).addProduct(validId);
    }

    /// СЦЕНАРИЙ 3: Метод getUserBasket возвращает пустую корзину, если ProductBasket пуст
    @Test
    void getUserBasket_ShouldReturnEmptyUserBasket_WhenProductBasketIsEmpty() {
        /// критерий - мок ProductBasket возвращает пустую мапу
        when(productBasket.getBasketItems()).thenReturn(Collections.emptyMap());

        UserBasket userBasket = basketService.getUserBasket();

        /// критерий - присутствуют проверки assertThat(userBasket.getItems()).isEmpty() и total == 0
        assertThat(userBasket.getItems()).isEmpty();
        assertThat(userBasket.getTotal()).isZero();
    }

    /// СЦЕНАРИЙ 4: Метод getUserBasket возвращает подходящую корзину, если в ProductBasket есть товары
    @Test
    void getUserBasket_ShouldReturnCorrectBasket_WhenProductBasketHasItems() {
        UUID productId = UUID.randomUUID();
        int count = 3;
        int price = 2500;
        Product mockProduct = new SimpleProduct(productId, "Мяч", price);

        /// критерий - мок ProductBasket возвращает мапу id -> количество
        when(productBasket.getBasketItems()).thenReturn(Map.of(productId, count));
        /// критерий - мок StorageService возвращает продукт с ценой
        when(storageService.getProductById(productId)).thenReturn(Optional.of(mockProduct));

        UserBasket userBasket = basketService.getUserBasket();

        /// критерий - выполнена проверка размера списка и total = цена * количество
        assertThat(userBasket.getItems()).hasSize(1);
        assertThat(userBasket.getTotal()).isEqualTo(price * count);

        // проверка детального наполнение элемента корзины
        assertThat(userBasket.getItems().get(0).getProduct()).isEqualTo(mockProduct);
        assertThat(userBasket.getItems().get(0).getCount()).isEqualTo(count);
    }
}