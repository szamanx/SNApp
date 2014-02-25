package snapp.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: daniel
 * Date: 23/02/14
 * Time: 18:46
 * To change this template use File | Settings | File Templates.
 */
public class User {

    private String username;

    private List<User> followedUsers = new ArrayList<>();

    private List<Message> messages = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<User> getFollowedUsers() {
        return followedUsers;
    }

    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User that = (User) o;

        return Objects.equals(this.username, that.username) &&
            Objects.equals(this.followedUsers, that.followedUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.username, followedUsers);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
