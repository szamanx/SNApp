package snapp.service;

import snapp.domain.Message;
import snapp.domain.User;

import java.util.List;

public interface CommunicationService {

    void post(String username, String message);

    boolean isUserExists(String username);

    List<Message> getWall(String username);

    List<Message> readTimeline(String username);

    void follow(String username, String usernameToFollow);
}
