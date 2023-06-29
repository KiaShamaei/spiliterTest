package snapp.pay.repositories;

import snapp.pay.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 *
 * @Author Kiarash Shamaei 2023-06-25
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByEmailIn(List<String> emails);

    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);

}
