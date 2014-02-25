package snapp.controller.command.runner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import snapp.controller.command.Command;
import snapp.controller.command.CommandRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SerialCommandRunnerTest {

    private CommandRunner runner = new SerialCommandRunner();

    @Spy
    private Command command = new Command() {
        @Override
        public void execute() {

        }
    };

    @Test
    public void testRunCommand() throws Exception {

        runner.runCommand(command);

        verify(command, only()).execute();

    }
}
