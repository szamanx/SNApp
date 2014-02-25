package snapp.controller.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import snapp.controller.command.types.PostingCommand;
import snapp.controller.command.types.UnsupportedCommand;
import snapp.controller.command.types.WallCommand;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: daniel
 * Date: 25/02/14
 * Time: 02:13
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-integration-context.xml")
public class CommandFactoryIT {

    private static final String POST_COMMAND1 = "Bob -> Message1";

    private static final String WALL_COMMAND1 = "Bob wall";

    private static final String UNSUPPORTED_COMMAND1 = "Unsupported command";

    @Autowired
    private CommandFactory commandFactory;

    @Test
    public void testCreatePostCommand() throws Exception {
        Command command = commandFactory.create(POST_COMMAND1);

        assertThat(command, instanceOf(PostingCommand.class));

    }

    @Test
    public void testCreateWallCommand() throws Exception {
        Command command = commandFactory.create(WALL_COMMAND1);

        assertThat(command, instanceOf(WallCommand.class));

    }

    @Test
    public void testCreateUnsupportedCommand() throws Exception {
        Command command = commandFactory.create(UNSUPPORTED_COMMAND1);

        assertThat(command, instanceOf(UnsupportedCommand.class));

    }
}
