package web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.DAO.RoleDAO;
import web.models.Role;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleDAO roleDao;

    @Autowired
    public RoleServiceImp(RoleDAO roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public Role getRole(String name) {
        return roleDao.findByRoleName(name);
    }
}
