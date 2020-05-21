package service;

import model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class UserService {

    private static UserService userService;

    /* хранилище данных */
    private Map<Long, User> dataBase = Collections.synchronizedMap(new HashMap<>());
    /* счетчик id */
    private AtomicLong maxId = new AtomicLong(0);
    /* список авторизованных пользователей */
    private Map<Long, User> authMap = Collections.synchronizedMap(new HashMap<>());


    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    private UserService() {
    }

    public List<User> getAllUsers() {
        List<User> getListAllUsersOfDatabase = dataBase
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
        return getListAllUsersOfDatabase;
    }

    public User getUserById(Long id) {
        User findUserById = dataBase
                .entrySet()
                .stream()
                .filter(e -> e.getKey() == id)
                .map(Map.Entry::getValue)
                .findFirst().get();
        return findUserById;
    }

    public boolean addUser(User user) {
        if (user.getEmail() == null || user.getPassword() == null || isExistsThisUser(user)) {
            return false;
        }
        user.setId(maxId.getAndIncrement());
        dataBase.put(user.getId(), user);
        return true;
    }


    public void deleteAllUser() {
        dataBase.clear();
    }

    public boolean isExistsThisUser(User user) {
        Optional<User> isExistShouldProhibit = dataBase
                .entrySet()
                .stream()
                .filter(e -> e.getValue().equals(user))
                .map(Map.Entry::getValue)
                .findFirst();

        return isExistShouldProhibit.isPresent();
    }

    public List<User> getAllAuth() {
        List<User> getListAllAuthUsers = authMap
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
        return getListAllAuthUsers;
    }

    public boolean authUser(User user) {
        if (user.getEmail() == null || user.getPassword() == null || !isExistsThisUser(user)) {
            return false;
        }

        User userFromDataVase = dataBase
                .values()
                .stream()
                .filter(e -> e.equals(user))
                .findFirst().get();

            authMap.put(userFromDataVase.getId(), userFromDataVase);
            return true;
    }

    public void logoutAllUsers() {
        authMap.clear();
    }

    public boolean isUserAuthById(Long id) {
        Optional<User> findUserById = authMap
                .entrySet()
                .stream()
                .filter(e -> e.getKey() == id)
                .map(Map.Entry::getValue)
                .findFirst();
        return findUserById.isPresent();
    }
}
