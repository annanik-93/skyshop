package org.skypro.skyshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkyshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkyshopApplication.class, args);
	}

}

/*
Критерии оценки: классы иммутабельны, используются стримы, зависимости инжектятся через конструкторы, а служебные поля скрыты из JSON
ссылки:
http://localhost:8080/products  - список продуктов
http://localhost:8080/articles  - список статей
http://localhost:8080/search?pattern=о   - поиск по букве "о"

*/