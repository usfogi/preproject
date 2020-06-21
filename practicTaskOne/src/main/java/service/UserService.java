package service;

import dataSet.UsersDataSet;

import java.util.List;

public interface UserService {
     void addUser(UsersDataSet user);
     List<UsersDataSet> getAllUsers();
     UsersDataSet getUserById(Long id);
     void updateUser(UsersDataSet user);
     void deleteUser(UsersDataSet user);
}
