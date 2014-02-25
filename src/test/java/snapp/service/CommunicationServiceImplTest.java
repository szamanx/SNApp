package snapp.service;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import snapp.domain.Message;
import snapp.domain.User;
import snapp.integration.UserDao;

import java.util.List;

import static org.hamcrest.Matchers.*;

import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommunicationServiceImplTest {

    private static final String USER_1 = "User1";

    private static final String USER_2 = "User2";

    private static final String USER_3 = "User3";

    private static final Function MESSAGE_TO_STRING = new Function<Message, String>() {
        @Override
        public String apply(Message input) {
            return input.getMessage();
        }
    };


    @InjectMocks
    private CommunicationServiceImpl communicationService = new CommunicationServiceImpl();

    @Mock
    private UserDao userDao;

    @Test
    public void testFollow() throws Exception {

        User u1 = new User();
        u1.setUsername(USER_1);

        User u2 = new User();
        u2.setUsername(USER_2);

        User u3 = new User();
        u3.setUsername(USER_3);

        when(userDao.findOne(USER_1)).thenReturn(u1);
        when(userDao.findOne(USER_2)).thenReturn(u2);
        when(userDao.findOne(USER_3)).thenReturn(u3);

        communicationService.follow(USER_1, USER_2);
        communicationService.follow(USER_1, USER_3);
        communicationService.follow(USER_1, USER_3);
        communicationService.follow(USER_1, USER_2);
        communicationService.follow(USER_2, USER_3);

        assertThat(u1.getFollowedUsers(), is(notNullValue()));
        assertThat(u1.getFollowedUsers(), hasSize(2));
        assertThat(u1.getFollowedUsers(), containsInAnyOrder(u2, u3));

        assertThat(u2.getFollowedUsers(), hasSize(1));
        assertThat(u3.getFollowedUsers(), hasSize(0));

        verify(userDao, times(10)).findOne(anyString());
    }

    @Test
    public void testGetWall() throws Exception {
        User u1 = new User();
        u1.setUsername(USER_1);

        User u2 = new User();
        u2.setUsername(USER_2);

        when(userDao.findOne(USER_1)).thenReturn(u1);
        when(userDao.findOne(USER_2)).thenReturn(u2);

        communicationService.follow(USER_1, USER_2);

        communicationService.post(USER_1, "Message11");
        communicationService.post(USER_1, "Message12");
        communicationService.post(USER_2, "Message21");
        communicationService.post(USER_1, "Message13");
        communicationService.post(USER_2, "Message22");

        List<Message> wall = communicationService.getWall(USER_1);

        List<String> messagesText = FluentIterable.from(wall).transform(MESSAGE_TO_STRING).toList();

        assertThat(messagesText, contains(""));

        assertThat(wall, hasSize(5));

        verify(userDao, times(8)).findOne(anyString());
    }

    @Test
    public void testPost() throws Exception {
        User u1 = new User();
        u1.setUsername(USER_1);

        when(userDao.findOne(USER_1)).thenReturn(u1);

        communicationService.post(USER_1, "Message1");
        communicationService.post(USER_1, "Message2");

        List<String> messagesText = FluentIterable.from(u1.getMessages()).transform(MESSAGE_TO_STRING).toList();

        assertThat(u1.getMessages(), hasSize(2));
        assertThat(messagesText, containsInAnyOrder("Message1", "Message2"));

        verify(userDao, times(2)).findOne(anyString());

    }

    @Test
    public void testReadTimeline() throws Exception {
        User u1 = new User();
        u1.setUsername(USER_1);

        User u2 = new User();
        u2.setUsername(USER_2);

        when(userDao.findOne(USER_1)).thenReturn(u1);
        when(userDao.findOne(USER_2)).thenReturn(u2);

        communicationService.follow(USER_1, USER_2);

        communicationService.post(USER_1, "Message11");
        communicationService.post(USER_1, "Message12");
        communicationService.post(USER_2, "Message21");
        communicationService.post(USER_1, "Message13");

        List<Message> timeline = communicationService.readTimeline(USER_1);

        List<String> messagesText = FluentIterable.from(timeline).transform(MESSAGE_TO_STRING).toList();

        assertThat(timeline, hasSize(3));

        verify(userDao, times(7)).findOne(anyString());
    }
}
