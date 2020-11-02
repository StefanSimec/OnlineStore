package ch.roche.ss.onlinestore.data.repository;

import ch.roche.ss.onlinestore.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Basic interface for accessing {@link Order} JPA data.
 */
public interface OrderRepository extends JpaRepository<Order, Long>
{
    /**
     * Finds Orders that have a placedDate between specified time values.
     *
     * @param dateFrom Date from
     * @param dateTo Date to
     * @return List of {@link Order}
     */
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.products WHERE o.placedDate BETWEEN :from AND :to ")
    List<Order> findByDates(@Param("from") OffsetDateTime dateFrom, @Param("to") OffsetDateTime dateTo);
}
