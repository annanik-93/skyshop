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
ПРОВЕРКА:
- http://localhost:8080/products
JSON-список товаров - cкопировать UUID любого сгенерированного продукта - 22ad49a5-0c75-4f02-95a1-11f8b54d5c3b
- создать новую вкладку в обычном режиме браузера и перейти по адресу:
http://localhost:8080/basket/СКОПИРОВАННЫЙ_UUID
http://localhost:8080/basket/22ad49a5-0c75-4f02-95a1-11f8b54d5c3b
- на экране отобразится текст: "Продукт успешно добавлен"
Выполните этот запрос пару раз, чтобы увеличилось количество
- открыть http://localhost:8080/basket
Браузер отобразит структурированный JSON со списком товаров,их количеством(count) и итоговой стоимостью всей корзины(total)
- проверка сессий (@SessionScope):
http://localhost:8080/basket - скопировать ссылку и открыть её в режиме инкогнито (или в другом браузере)
- Корзина там окажется полностью пустой (items: [], total: 0)
Изменения из первой вкладки никак не повлияли на вторую, критерий изоляции сессий полностью соблюден


*/