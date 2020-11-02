package ch.roche.ss.onlinestore.service.mapper;

import ch.roche.ss.onlinestore.api.dto.OrderDTO;
import ch.roche.ss.onlinestore.api.dto.ProductDTO;
import ch.roche.ss.onlinestore.data.model.Order;
import ch.roche.ss.onlinestore.data.model.Product;
import org.aspectj.lang.annotation.After;
import org.mapstruct.*;

import java.math.BigDecimal;

/**
 * <b>MapStruct</b> interface for mapping between entity and DTO.
 * @see <a href="https://mapstruct.org/documentation/stable/api/">MapStruct</a>}
 */
@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface OrderMapper
{
    @Mappings(
            @Mapping(target="products", source = "productDTOs")
    )
    Order toOrder(OrderDTO orderDTO);
    @Mappings(
        {
            @Mapping(target="productDTOs", source = "products"),
        }
    )
    OrderDTO toOrderDTO(Order order);

    @AfterMapping
    default void calculateOrderTotal(final Order order, @MappingTarget OrderDTO.OrderDTOBuilder orderDto)
    {
        if (order.getProducts() != null)
        {
            BigDecimal total = order.getProducts().stream()
                    .filter(p -> p != null)
                    .map(Product::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            orderDto.orderTotal(total);
        }
        else
        {
            orderDto.orderTotal(BigDecimal.ZERO);
        }
    }
}
