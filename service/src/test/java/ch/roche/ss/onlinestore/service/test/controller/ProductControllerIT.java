package ch.roche.ss.onlinestore.service.test.controller;

import ch.roche.ss.onlinestore.data.model.Product;
import ch.roche.ss.onlinestore.data.repository.ProductRepository;
import ch.roche.ss.onlinestore.service.OnlineStoreApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = OnlineStoreApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude= SecurityAutoConfiguration.class)
@AutoConfigureTestDatabase
public class ProductControllerIT
{
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductRepository repository;

    @Before
    @After
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    public void getProductTest() throws Exception
    {
        Product testProduct = saveProduct(3L);

        mvc.perform(get("/products/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sku").value(3L));
    }

    @Test
    public void getAllProductsTest() throws Exception
    {

        Product testProduct = saveProduct(4L);

        mvc.perform(get("/products/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.productDToes", hasSize(1)))
                .andExpect(jsonPath("$._embedded.productDToes[0].sku").value(4L));
    }

    @Test
    @Transactional
    public void deleteProductTest() throws Exception
    {
        Product testProduct = saveProduct(5L);

        mvc.perform(delete("/products/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get("/products/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Transactional
    public void saveProductTest() throws Exception
    {
        String productJson="{\n" +
                "  \"sku\": 6,\n" +
                "  \"name\": \"Product 6\",\n" +
                "  \"price\": 10\n" +
                "}";

        mvc.perform(post("/products/")
                .accept(MediaType.APPLICATION_JSON)
                .content(productJson)
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        Product product = repository.getOne(6L);
        assertThat(product.getName()).isEqualTo("Product 6");
        assertThat(product.getPrice()).isEqualTo(BigDecimal.TEN);

    }

    @Test
    @Transactional
    public void updateProductTest() throws Exception
    {
        String productJson="{\n" +
                "  \"sku\": 7,\n" +
                "  \"name\": \"Product 7\",\n" +
                "  \"price\": 20\n" +
                "}";

        mvc.perform(post("/products/")
                .accept(MediaType.APPLICATION_JSON)
                .content(productJson)
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        Product product = repository.getOne(7L);
        assertThat(product.getName()).isEqualTo("Product 7");
        assertThat(product.getPrice()).isEqualTo(new BigDecimal(20));

    }

    private Product saveProduct(Long i) {
        Product testProduct = Product.builder()
                .id(i)
                .name("Test product "+i)
                .price(BigDecimal.TEN)
                .deleted(false)
                .build();

        return repository.saveAndFlush(testProduct);
    }
}
