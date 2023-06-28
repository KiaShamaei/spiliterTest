package snapp.pay.repositories;

import snapp.pay.entities.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @Author Kiarash Shamaei 2023-06-25
 */

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

}
