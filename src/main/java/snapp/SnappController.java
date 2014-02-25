package snapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import snapp.controller.command.CommandFactory;
import snapp.controller.command.CommandRunner;

import java.io.Console;

public class SnappController {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(SnappController.class);

        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"app-context.xml"});

        CommandFactory commandFactory = (CommandFactory) context.getBean("snapp.command.factory");
        CommandRunner runner = (CommandRunner) context.getBean("snapp.runner");

        Console console = System.console();
        if (console == null) {
            System.err.println("Console did not start");
            System.exit(1);
        }

        String textCommand = "";

        while(true) {
            textCommand = console.readLine(">");

            runner.runCommand(commandFactory.create(textCommand));
        }

    }
}
