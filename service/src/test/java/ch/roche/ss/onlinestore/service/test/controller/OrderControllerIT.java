package ch.roche.ss.onlinestore.service.test.controller;

import ch.roche.ss.onlinestore.data.model.Order;
import ch.roche.ss.onlinestore.data.model.Product;
import ch.roche.ss.onlinestore.data.repository.OrderRepository;
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
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = OnlineStoreApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude= SecurityAutoConfiguration.class)
@AutoConfigureTestDatabase
public class OrderControllerIT
{
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository repository;

    @Before
    @After
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    @Transactional
    public void placeOrderTest() throws Exception
    {
        String orderJson="{\n" +
                "  \"id\": 1,\n" +
                "  \"email\": \"test@test.com\",\n" +
                "  \"productDTOs\": [{\"sku\": 1}, {\"sku\": 2}]\n" +
                "}";

        mvc.perform(post("/orders/place")
                .accept(MediaType.APPLICATION_JSON)
                .content(orderJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Order order=repository.getOne(1L);
        assertThat(order.getId()).isEqualTo(1L);
        assertThat(order.getEmail()).isEqualTo("test@test.com");
        assertThat(order.getProducts()).hasSize(2);
    }

    @Test
    @Transactional
    public void getOrderTest() throws Exception
    {
        Product product1 = productRepository.getOne(1L);
        Product product2 = productRepository.getOne(2L);
        Order order = Order.builder()
                .id(2L)
                .email("test2@test.com")
                .placedDate(OffsetDateTime.of(2020, 1, 1, 12, 0, 0, 0, ZoneOffset.UTC))
                .products(List.of(product1, product2))
                .build();

        repository.save(order);

        mvc.perform(get("/orders/findByTimePeriod")
                .param("dateFrom", "2019-11-01T22:20:36.724Z")
                .param("dateTo", "2021-11-01T22:20:36.724Z")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
