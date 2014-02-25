package snapp.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;

import java.util.Objects;

public class Message {

    private String message;

    private DateTime timestamp;

    //reversed relation
    private User user;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;

        Message that = (Message) o;

        return Objects.equals(this.message, that.message) &&
                Objects.equals(this.timestamp, that.timestamp) &&
                Objects.equals(this.user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.message, this.timestamp, this.user);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
