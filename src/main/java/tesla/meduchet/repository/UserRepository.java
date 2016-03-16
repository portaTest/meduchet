package tesla.meduchet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tesla.meduchet.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
