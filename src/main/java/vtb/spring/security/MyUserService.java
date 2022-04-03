package vtb.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import vtb.spring.model.User;
import vtb.spring.repository.interfaces.UserRepository;

@Component
public class MyUserService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public MyUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User userInDb = repository.findByLogin(username);
        if(userInDb == null ) throw new UsernameNotFoundException("Пользователя не существует");
        return userInDb;
    }
}
