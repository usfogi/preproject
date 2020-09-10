package web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.DAO.UserDAO;
import web.models.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    private UserDAO userDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return (List<User>) userDao.findAll();
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        String passwordFromDB = getUsersById(user.getId()).getPassword();
        if (!passwordEncoder.matches(user.getPassword(), passwordFromDB)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDao.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        userDao.deleteById(id);
    }

    @Override
    @Transactional
    public User getUsersById(long id) {
        return userDao.findById(id).get();
    }

    @Transactional
    @Override
    public boolean add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return true;
    }

    @Transactional
    @Override
    public User loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    @Transactional
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            user = loadUserByUsername(userDetails.getUsername());
        }
        return user;
    }
}
