package service;

import model.User;
import repository.UserRepository;
import java.util.List;

public class UserService {
    private UserRepository userRepository = new UserRepository();
    private int nextUserId = 3;

    public User login(String username, String password) {
        for (User u : userRepository.findAll()) {
            if (u.getUserName().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public User register(String username, String livingCity, String password) {
        for (User u : userRepository.findAll()) {
            if (u.getUserName().equalsIgnoreCase(username)) {
                System.out.println("Registration failed: Username '" + username + "' is already taken.");
                return null;
            }
        }

        User newUser = new User(nextUserId++, username, livingCity, password, false);
        userRepository.save(newUser);

        System.out.println("User registered successfully!");
        return newUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}