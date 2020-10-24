package com.victorze.secondhandmarket;

import com.victorze.secondhandmarket.models.Product;
import com.victorze.secondhandmarket.models.User;
import com.victorze.secondhandmarket.services.ProductService;
import com.victorze.secondhandmarket.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SecondHandMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondHandMarketApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(UserService userService, ProductService productService) {
		return args -> {

			User user = new User("Victor Hugo", "López", null, "vic@mail.com", "vic");
			user = userService.save(user);

			User user2 = new User("Mario", "García", null, "mario.garcia@mail.net", "mario");
			user2 = userService.save(user2);

			List<Product> listado = Arrays.asList(
					new Product("Bicicleta de montaña", new BigDecimal("100.0"), "https://www.decathlon.es/media/835/8350582/big_23c25284-2810-415d-8bcc-e6bebdb536fc.jpg", user),
					new Product("Golf GTI Serie 2", new BigDecimal("2500"), "https://www.minicar.es/large/Volkswagen-Golf-GTi-G60-Serie-II-%281990%29-Norev-1%3A18-i22889.jpg", user),
					new Product("Raqueta de tenis", new BigDecimal("10.5"), "https://imgredirect.milanuncios.com/fg/2311/04/tenis/Raqueta-tenis-de-segunda-mano-en-Madrid-231104755_1.jpg?VersionId=T9dPhTf.3ZWiAFjnB7CvGKsvbdfPLHht", user),
					new Product("Xbox One X", new BigDecimal("425"), "https://images.vibbo.com/635x476/860/86038583196.jpg", user2),
					new Product("Trípode flexible", new BigDecimal("10"), "https://images.vibbo.com/635x476/860/86074256163.jpg", user2),
					new Product("Iphone 7 128 GB", new BigDecimal("350"), "https://store.storeimages.cdn-apple.com/4667/as-images.apple.com/is/image/AppleInc/aos/published/images/i/ph/iphone7/rosegold/iphone7-rosegold-select-2016?wid=470&hei=556&fmt=jpeg&qlt=95&op_usm=0.5,0.5&.v=1472430205982", user2));

			listado.forEach(productService::save);
		};
	}

}
