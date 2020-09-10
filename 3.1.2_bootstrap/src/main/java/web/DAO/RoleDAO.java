package web.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.models.Role;

@Repository
public interface RoleDAO extends CrudRepository<Role, Long> {

    Role findByRoleName(String roleName);
}
