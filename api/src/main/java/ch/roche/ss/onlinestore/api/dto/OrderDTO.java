package ch.roche.ss.onlinestore.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(of="id")
@Builder
public class OrderDTO
{

    @EqualsAndHashCode.Include
    @NotNull(message = "ID is required")
    private Long id;

    @NotBlank(message = "Email is required")
    @Email(message = "Not a valid email")
    private String email;

    private OffsetDateTime placedDate;
    private List<ProductDTO> productDTOs;
    private BigDecimal orderTotal;
}
