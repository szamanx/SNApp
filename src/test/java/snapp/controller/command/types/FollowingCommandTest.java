package snapp.controller.command.types;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import snapp.service.CommunicationService;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FollowingCommandTest {

    private static final String USER_1 = "User1";
    private static final String USER_2 = "User2";

    private FollowingCommand followingCommand;

    @Mock
    private CommunicationService communicationService;

    @Test
    public void testCommandExecution() {

        doNothing().when(communicationService).follow(USER_1, USER_2);

        followingCommand = FollowingCommand.builder().withCommunicationService(communicationService).withUsername(USER_1).withUsernameToFollow(USER_2).build();

        followingCommand.execute();

        verify(communicationService, only()).follow(anyString(), anyString());
    }

}
