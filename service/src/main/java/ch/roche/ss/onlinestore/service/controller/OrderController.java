package ch.roche.ss.onlinestore.service.controller;

import ch.roche.ss.onlinestore.api.dto.OrderDTO;
import ch.roche.ss.onlinestore.api.service.OrderServiceAPI;
import ch.roche.ss.onlinestore.data.model.Order;
import ch.roche.ss.onlinestore.data.model.Product;
import ch.roche.ss.onlinestore.data.repository.ProductRepository;
import ch.roche.ss.onlinestore.service.mapper.OrderMapper;
import ch.roche.ss.onlinestore.data.repository.OrderRepository;
import ch.roche.ss.onlinestore.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * RPC Controller for handling Order API calls
 *
 * @see OrderServiceAPI
 */
@RestController
public class OrderController implements OrderServiceAPI {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper mapper;

    @Override
    public OrderDTO placeOrder(OrderDTO orderDTO)
    {
        Order order = mapper.toOrder(orderDTO);
        order=orderService.createOrder(order);
        return mapper.toOrderDTO(order);
    }

    @Override
    public List<OrderDTO> retrieveOrders(OffsetDateTime dateFrom, OffsetDateTime dateTo)
    {
        return orderService.findByDates(dateFrom,dateTo).stream()
                .map(o->mapper.toOrderDTO(o))
                .collect(Collectors.toList());
    }
}
