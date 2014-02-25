package snapp.integration;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import org.springframework.beans.factory.annotation.Required;
import snapp.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{

    private List<User> users = new ArrayList<>();

    @Override
    public User findOne(final String username) {
        return FluentIterable.from(users).firstMatch(new Predicate<User>() {
            @Override
            public boolean apply(User user) {
                return user.getUsername().equals(username);
            }
        }).orNull();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
