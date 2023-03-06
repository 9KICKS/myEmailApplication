package data.repositories;

import data.models.User;

import java.util.List;

public interface UserRepository {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    User findUserByUsername(String username);
    List<User> findAllUsers();
}