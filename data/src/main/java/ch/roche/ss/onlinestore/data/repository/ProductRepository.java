package ch.roche.ss.onlinestore.data.repository;

import ch.roche.ss.onlinestore.data.model.Order;
import ch.roche.ss.onlinestore.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Basic interface for accessing {@link Product} JPA data.
 */
public interface ProductRepository extends JpaRepository<Product, Long>
{

}
