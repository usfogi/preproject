package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    void add(User user);
    List<User> listUsers();
    void delete(Long id);
    void edit(User user);
    User getById(Long id);
}
