package repository;

import model.User;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> userDatabase = new ArrayList<>();

    public UserRepository() {
        userDatabase.add(new User(1, "admin", "New York", "admin123", true));
        userDatabase.add(new User(2, "student", "London", "pass123", false));
    }

    public void save(User user) {
        userDatabase.add(user);
    }

    public List<User> findAll() {
        return userDatabase;
    }
}