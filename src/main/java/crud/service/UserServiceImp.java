package crud.service;

import crud.model.User;
import crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImp implements UserService{

    private final UserRepository userRepo;

    @Autowired
    public UserServiceImp(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserById(Long id) {
        return userRepo.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserByName(String name) {
        return userRepo.findUserByUsername(name);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Transactional
    @Override
    public void save(User user) {
        userRepo.save(user);
    }
}
