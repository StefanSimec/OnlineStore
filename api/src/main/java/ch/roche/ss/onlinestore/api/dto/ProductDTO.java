package ch.roche.ss.onlinestore.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(of="sku")
@Builder
public class ProductDTO
{

    @EqualsAndHashCode.Include
    @NotNull(message = "SKU is required")
    private Long sku;

    @NotBlank(message = "Name is required")
    @Length(max=50, message = "Maximum length is 50 characters")
    private String name;

    @NotNull
    @DecimalMin(value = "0", message = "Minimum value is '0'")
    private BigDecimal price;

}
