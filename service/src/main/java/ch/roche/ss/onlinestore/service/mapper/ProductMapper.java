package ch.roche.ss.onlinestore.service.mapper;

import ch.roche.ss.onlinestore.api.dto.ProductDTO;
import ch.roche.ss.onlinestore.data.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * <b>MapStruct</b> interface for mapping between entity and DTO.
 * @see <a href="https://mapstruct.org/documentation/stable/api/">MapStruct</a>}
 */
@Mapper(componentModel = "spring")
public interface ProductMapper
{
    @Mappings(
            @Mapping(target = "id", source = "sku")
    )
    Product toProduct(ProductDTO productDTO);
    @Mappings(
            @Mapping(target = "sku", source = "id")
    )
    ProductDTO toProductDTO(Product product);

}
