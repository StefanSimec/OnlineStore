package ch.roche.ss.onlinestore.service.service;

import ch.roche.ss.onlinestore.data.model.Order;
import ch.roche.ss.onlinestore.data.model.Product;
import ch.roche.ss.onlinestore.data.repository.OrderRepository;
import ch.roche.ss.onlinestore.data.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for Order manipulation
 */
@Service
@Slf4j
public class OrderService
{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    /**
     * Creates new order with specified products
     *
     * @param order input Order
     * @return created Order
     */
    public Order createOrder(Order order)
    {
        log.info("Placing new order: "+order);
        order.setPlacedDate(OffsetDateTime.now());
        List<Product> products = order.getProducts().stream()
                .map(p -> productRepository.findById(p.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        order.setProducts(products);
        return orderRepository.save(order);
    }

    public List<Order> findByDates(OffsetDateTime dateFrom, OffsetDateTime dateTo)
    {
        log.info("Searching for orders from: "+dateFrom+" to "+dateTo);
        return orderRepository.findByDates(dateFrom, dateTo);
    }
}
