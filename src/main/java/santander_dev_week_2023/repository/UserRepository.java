package santander_dev_week_2023.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import santander_dev_week_2023.model.User;
@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    boolean existisByAccontNumber(String accontNumber);

}
