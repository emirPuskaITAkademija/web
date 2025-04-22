package com.itakademija.user;

import java.util.HashSet;
import java.util.Set;

public class UserRepository {
    private static final Set<User> users = new HashSet<User>();

    static {
        users.add(new User("tarik.fejzic@gmail.com", "tarik123", "Tarik", "Fejzic"));
        users.add(new User("jovan.carevic@gmail.com", "jovanCar123", "Jovan", "Carevic"));
    }

    public User login(String username, String password) {
        return users
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .filter(user -> user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
}
