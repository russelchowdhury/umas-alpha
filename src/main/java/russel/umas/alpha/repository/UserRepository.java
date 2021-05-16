package russel.umas.alpha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import russel.umas.alpha.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
