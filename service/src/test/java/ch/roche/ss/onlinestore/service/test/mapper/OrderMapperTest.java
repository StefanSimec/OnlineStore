package ch.roche.ss.onlinestore.service.test.mapper;

import ch.roche.ss.onlinestore.api.dto.OrderDTO;
import ch.roche.ss.onlinestore.api.dto.ProductDTO;
import ch.roche.ss.onlinestore.data.model.Order;
import ch.roche.ss.onlinestore.data.model.Product;
import ch.roche.ss.onlinestore.service.mapper.OrderMapper;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.*;

public class OrderMapperTest
{
    private OrderMapper mapper = Mappers.getMapper(OrderMapper.class);

    @Test
    public void orderToOrderDTOTest()
    {
        Order order = Order.builder()
                .id(1L)
                .email("test@test.com")
                .placedDate(OffsetDateTime.now())
                .build();

        OrderDTO orderDTO = mapper.toOrderDTO(order);

        assertThat(order.getId()).isEqualTo(orderDTO.getId());
        assertThat(order.getEmail()).isEqualTo(orderDTO.getEmail());
        assertThat(order.getPlacedDate()).isEqualTo(orderDTO.getPlacedDate());
        assertThat(orderDTO.getOrderTotal()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void orderDTOToOrderTest()
    {

        OrderDTO orderDTO = OrderDTO.builder()
                .id(1L)
                .email("test@test.com")
                .placedDate(OffsetDateTime.now())
                .build();

        Order order = mapper.toOrder(orderDTO);

        assertThat(orderDTO.getId()).isEqualTo(order.getId());
        assertThat(orderDTO.getEmail()).isEqualTo(order.getEmail());
        assertThat(orderDTO.getPlacedDate().equals(order.getPlacedDate()));
    }

}
