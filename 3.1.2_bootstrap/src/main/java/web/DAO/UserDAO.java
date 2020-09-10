package web.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.models.User;

@Repository
public interface UserDAO extends CrudRepository<User, Long> {

    @Query("select u from User u left join fetch u.roles where u.username = :name")
    User findByUsername(@Param("name") String username);

}
