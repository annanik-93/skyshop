package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Критерий: Используется аннотация ExtendWith
class SearchServiceTest {

    @Mock /// критерий - использован @Mock для StorageService
    private StorageService storageService;

    @InjectMocks /// критерий - использован @InjectMocks для SearchService
    private SearchService searchService;

    /// СЦЕНАРИЙ 1 - поиск в случае отсутствия объектов в StorageService
    @Test
    void search_ShouldReturnEmptyList_WhenStorageIsEmpty() {
        /// критерий - мок StorageService возвращает пустую коллекцию
        when(storageService.getAllSearchables()).thenReturn(Collections.emptyList());

        Collection<SearchResult> results = searchService.search("ЛюбойЗапрос");

        /// критерий - используется assertThat(results).isEmpty()
        assertThat(results).isEmpty();
    }

    /// СЦЕНАРИЙ 2 - Объекты в StorageService есть, но нет подходящего !
    @Test
    void search_ShouldReturnEmptyList_WhenStorageHasNoMatchingObjects() {
        Product testProduct = new SimpleProduct(UUID.randomUUID(), "Смартфон", 50000);
        /// критерий - возвращает коллекцию с тестовыми объектами
        when(storageService.getAllSearchables()).thenReturn(List.of(testProduct));

        /// критерий - используется assertThat(results).isEmpty() при несовпадающем pattern
        Collection<SearchResult> results = searchService.search("Холодильник");

        assertThat(results).isEmpty();
    }

    /// СЦЕНАРИЙ 3 - Когда есть подходящий объект в StorageService !
    @Test
    void search_ShouldReturnMatchingObject_WhenStorageHasMatchingObjects() {
        /// критерий - мок возвращает объект с именем "TestProduct"
        Product testProduct = new SimpleProduct(UUID.randomUUID(), "TestProduct", 1500);
        when(storageService.getAllSearchables()).thenReturn(List.of(testProduct));

        /// критерий - поиск с pattern = "Test" возвращает список размером 1
        Collection<SearchResult> results = searchService.search("Test");

        assertThat(results).hasSize(1);

        // дополнительная проверка корректности маппинга полей в SearchResult
        SearchResult matchingResult = results.iterator().next();
        assertThat(matchingResult.getId()).isEqualTo(testProduct.getId().toString());
        assertThat(matchingResult.getName()).isEqualTo("TestProduct");
        assertThat(matchingResult.getContentType()).isEqualTo("PRODUCT");
    }
}