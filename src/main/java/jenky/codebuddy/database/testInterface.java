package jenky.codebuddy.database;

import jenky.codebuddy.models.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by joost on 7-6-2016.
 */
public interface testInterface extends CrudRepository<User, Long> {

    List<User> findByEmail(String email);

}
