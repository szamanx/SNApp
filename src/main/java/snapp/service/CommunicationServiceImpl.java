package snapp.service;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Multimap;
import com.google.common.collect.Ordering;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import snapp.domain.Message;
import snapp.domain.User;
import snapp.domain.Wall;
import snapp.integration.UserDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: daniel
 * Date: 23/02/14
 * Time: 19:07
 * To change this template use File | Settings | File Templates.
 */
public class CommunicationServiceImpl implements CommunicationService {

    private static final Ordering<Message> MESSAGE_ORDERING = Ordering.natural().onResultOf(new Function<Message, DateTime>() {
        @Override
        public DateTime apply(Message input) {
            return input.getTimestamp();
        }
    }).reverse();

    private static Logger logger = LoggerFactory.getLogger(CommunicationServiceImpl.class);

    private UserDao userDao;

    @Override
    public void follow(String username, String usernameToFollow) {
        User user = getUser(username);
        final User userToFollow = getUser(usernameToFollow);
        if(user != null && userToFollow != null) {
            Optional<User> alreadyFollowedUser = FluentIterable.from(user.getFollowedUsers()).firstMatch(new Predicate<User>() {
                @Override
                public boolean apply(User input) {
                    return input.equals(userToFollow);
                }
            });

            if(!alreadyFollowedUser.isPresent()) {
                logger.debug(String.format("Following: User: %s follow: user %s", username, usernameToFollow));
                user.getFollowedUsers().add(userToFollow);
            }
            else {
                logger.debug(String.format("Following: User: %s already follow: user %s", username, usernameToFollow));
            }
        }

    }

    @Override
    public List<Message> getWall(String username) {
        User user = getUser(username);
        List<Message> messages = new ArrayList<>();
        if(user != null) {
            messages.addAll(user.getMessages());

            for(User followedUser : user.getFollowedUsers()) {
                messages.addAll(followedUser.getMessages());
            }
        }

        return MESSAGE_ORDERING.sortedCopy(messages);
    }

    @Override
    public boolean isUserExists(String username) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void post(String username, String msg) {
        User user = getUser(username);
        if(user != null) {
            Message message = new Message();
            message.setTimestamp(DateTime.now());
            message.setMessage(msg);
            message.setUser(user);

            user.getMessages().add(message);
            logger.debug(String.format("Posting: User: %s, Message: %s", username, msg));
        }
        else {
            logger.debug(String.format("Posting: User: %s does not exists", username));
        }

    }

    @Override
    public List<Message> readTimeline(String username) {
        User user = getUser(username);

        if(user != null) {
            logger.debug(String.format("Read: User: %s, Message count: %d", username, user.getMessages().size()));
            return MESSAGE_ORDERING.sortedCopy(user.getMessages());
        }
        logger.debug(String.format("Read: User: %s does not exists", username));
        return new ArrayList<>();
    }

    private User getUser(String username) {
        return userDao.findOne(username);
    }

    @Required
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
