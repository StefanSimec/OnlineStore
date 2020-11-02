package ch.roche.ss.onlinestore.data.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Where(clause = "NVL2(deleted,deleted,false) = false")
@SQLDelete(sql="UPDATE Product SET deleted = true WHERE id=?")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class Product extends BaseEntity
{
    private String name;
    private BigDecimal price;
    private Boolean deleted;

}
