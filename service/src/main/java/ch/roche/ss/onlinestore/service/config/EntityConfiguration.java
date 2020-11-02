package ch.roche.ss.onlinestore.service.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "ch.roche.ss.onlinestore.data")
@EntityScan(basePackages = "ch.roche.ss.onlinestore.data")
@EnableJpaRepositories(basePackages = "ch.roche.ss.onlinestore.data")
public class EntityConfiguration {
}
