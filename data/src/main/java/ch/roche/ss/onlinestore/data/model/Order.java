package ch.roche.ss.onlinestore.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "ORDER_")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class Order extends BaseEntity
{
    private String email;
    @ManyToMany(targetEntity = Product.class, fetch = FetchType.LAZY)
    private List<Product> products;
    private OffsetDateTime placedDate;

}
