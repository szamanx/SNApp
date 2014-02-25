package snapp.integration;

import snapp.domain.User;

public interface UserDao {

    User findOne(String username);

}
