package org.skypro.skyshop.model.search;

// создаем неизменяемую модель для JSON-ответа результатов поиска
public class SearchResult {
    private final String id;
    private final String name;
    private final String contentType;

    public SearchResult(String id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    /// сеттеры отсутствуют, поля final — класс иммутабелен, добавляем только геттеры для JSON
    public String getId() { return id; }
    public String getName() { return name; }
    public String getContentType() { return contentType; }

    /// статический фабричный метод преобразования
    public static SearchResult fromSearchable(Searchable searchable) {
        return new SearchResult(
                searchable.getId().toString(),
                searchable.getName(),
                searchable.getContentType()
        );
    }
}