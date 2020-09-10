package web.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import web.models.User;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(long id);
    User getUsersById(long id);
    boolean add(User user);
    UserDetails loadUserByUsername(String username);
    User getAuthenticatedUser();
}
