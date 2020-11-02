package ch.roche.ss.onlinestore.service.test.mapper;

import ch.roche.ss.onlinestore.api.dto.ProductDTO;
import ch.roche.ss.onlinestore.data.model.Order;
import ch.roche.ss.onlinestore.data.model.Product;
import ch.roche.ss.onlinestore.service.mapper.ProductMapper;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;

public class ProductMapperTest
{
    private ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Test
    public void productToProductDTOTest()
    {
        Product product=Product.builder()
                .id(1L)
                .name("Product 1")
                .deleted(false)
                .price(new BigDecimal(10))
                .build();

        ProductDTO productDTO = mapper.toProductDTO(product);

        assertThat(product.getId()).isEqualTo(productDTO.getSku());
        assertThat(product.getName()).isEqualTo(productDTO.getName());
        assertThat(product.getPrice()).isEqualTo(productDTO.getPrice());
    }

    @Test
    public void productDTOToProductTest()
    {
        ProductDTO productDTO=ProductDTO.builder()
                .sku(1L)
                .name("Product 1")
                .price(new BigDecimal(10L))
                .build();

        Product product = mapper.toProduct(productDTO);

        assertThat(productDTO.getSku()).isEqualTo(product.getId());
        assertThat(productDTO.getName()).isEqualTo(product.getName());
        assertThat(productDTO.getPrice()).isEqualTo(product.getPrice());
    }
}
