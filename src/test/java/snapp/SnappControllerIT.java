package snapp;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import snapp.controller.command.Command;
import snapp.controller.command.CommandFactory;
import snapp.controller.command.CommandRunner;
import snapp.controller.message.renderer.MessageRenderer;

import java.io.ByteArrayOutputStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.startsWith;

/**
 * Unit test for simple SnappController.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-integration-context.xml")
public class SnappControllerIT {

    private static final String POST_COMMAND1 = "Bob -> Message1";

    private static final String WALL_COMMAND1 = "Bob wall";

    @Autowired
    private CommandFactory commandFactory;

    @Autowired
    private CommandRunner runner;

    @Autowired
    private ByteArrayOutputStream stream;

    @Test
    public void applicationContextTest() {
        assertTrue(true);
    }

    @Test
    public void testRun() {
        Command command = commandFactory.create(POST_COMMAND1);
        runner.runCommand(command);

        command = commandFactory.create(WALL_COMMAND1);
        runner.runCommand(command);

        assertThat(stream.toString(), is(startsWith("Bob - Message1")));

    }

}
