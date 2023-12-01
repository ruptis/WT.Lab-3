package by.bsuir.likeit.service;

import by.bsuir.likeit.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    User addUser(String username, String password, String email);

    User getUserById(long id);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    Page<User> getAllUsers(int page, int pageSize);

    List<User> getAllUsers();

    void banUser(long id);

    void unbanUser(long id);

    boolean isUserExists(String username, String email);
}
