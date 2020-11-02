package ch.roche.ss.onlinestore.service.config;

import ch.roche.ss.onlinestore.data.model.Product;
import ch.roche.ss.onlinestore.data.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@Slf4j
public class LoadDatabase
{
    @Bean
    CommandLineRunner initProducts(ProductRepository repository)
    {
        return args ->
        {
            log.info("Product load started...");

            Product product1 = Product.builder()
                    .id(1L)
                    .name("Product 1")
                    .price(new BigDecimal(10))
                    .build();
            log.info("Saving "+product1);
            repository.save(product1);

            Product product2 = Product.builder()
                    .id(2L)
                    .name("Product 2")
                    .price(new BigDecimal(20))
                    .build();
            log.info("Saving "+product2);
            repository.save(product2);
        };
    }
}
