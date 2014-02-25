package snapp.controller.command.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import snapp.controller.command.Command;
import snapp.controller.command.CommandRunner;

public class SerialCommandRunner implements CommandRunner {

    public void runCommand(Command command) {
        command.execute();
    }
}
