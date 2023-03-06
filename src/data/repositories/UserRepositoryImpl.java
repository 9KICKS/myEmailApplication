package data.repositories;

import data.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private List<User> users;

    public UserRepositoryImpl() {
        this.users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public void updateUser(User user) {
        int index = users.indexOf(user);
        if (index >= 0) {
            this.users.set(index, user);
        }
    }

    @Override
    public void deleteUser(User user) {
        this.users.remove(user);
    }

    @Override
    public User findUserByUsername(String username) {
        for (User user : this.users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return new ArrayList<>(this.users);
    }
}