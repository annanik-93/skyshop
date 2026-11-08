package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

// внедряем StorageService через конструктор и пишем логику фильтрации с помощью Stream API
@Service
public class SearchService {
    private final StorageService storageService;

    /// внедрение зависимости через конструктор
    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    /// методы Stream API по критериям оценки
    public Collection<SearchResult> search(String query) {
        return storageService.getAllSearchables().stream()
                .filter(item -> item.getSearchTerm().toLowerCase().contains(query.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }
}