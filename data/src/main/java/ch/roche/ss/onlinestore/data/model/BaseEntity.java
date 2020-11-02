package ch.roche.ss.onlinestore.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * Base entity class that all other entities should extend
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
public abstract class BaseEntity
{
    @EqualsAndHashCode.Include
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

}
