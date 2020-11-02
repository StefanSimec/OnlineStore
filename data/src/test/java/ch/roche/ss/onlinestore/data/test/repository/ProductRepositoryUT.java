package ch.roche.ss.onlinestore.data.test.repository;

import ch.roche.ss.onlinestore.data.model.Product;
import ch.roche.ss.onlinestore.data.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringRunner.class)
//@DataJpaTest
public class ProductRepositoryUT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    //@Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        Product testProduct = Product.builder()
                .id(1L)
                .name("Test product 1")
                .price(BigDecimal.TEN)
                .deleted(false)
                .build();

        entityManager.persist(testProduct);
        entityManager.flush();

        // when
        Product found = productRepository.getOne(testProduct.getId());

        // then
        assertThat(found).isEqualTo(testProduct);
    }
}
