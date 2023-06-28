package snapp.pay.repositories;

import snapp.pay.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @Author Kiarash Shamaei 2023-06-25
 */
@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

}
