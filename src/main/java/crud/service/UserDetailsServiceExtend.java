package crud.service;

import crud.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserDetailsServiceExtend implements UserDetailsService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = entityManager
                .createQuery("SELECT u FROM User u JOIN FETCH u.roles")
                .getResultList();

        User user = users.stream()
                        .filter(u -> u.getUsername()
                        .equals(username))
                        .findFirst()
                        .orElseThrow();

        if (user != null) {
            return user;
        }

        throw new UsernameNotFoundException(
                "User '" + username + "' not found");
    }
}
