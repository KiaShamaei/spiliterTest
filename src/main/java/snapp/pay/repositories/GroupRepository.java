package snapp.pay.repositories;

import snapp.pay.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @Author Kiarash Shamaei 2023-06-25
 */

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

}
