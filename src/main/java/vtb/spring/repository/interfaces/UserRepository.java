package vtb.spring.repository.interfaces;

import vtb.spring.model.User;

public interface UserRepository {

    User findByLogin(String login);

}
