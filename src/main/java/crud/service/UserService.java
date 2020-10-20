package crud.service;

import crud.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findUserById(Long id);
    Iterable<User> findAll();
    User findUserByName(String name);
    void deleteUser(Long id);
    void save(User user);
}
