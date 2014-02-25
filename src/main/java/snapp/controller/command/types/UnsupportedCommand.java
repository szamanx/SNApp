package snapp.controller.command.types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import snapp.controller.command.Command;

/**
 * Created with IntelliJ IDEA.
 * User: daniel
 * Date: 23/02/14
 * Time: 17:10
 * To change this template use File | Settings | File Templates.
 */
public class UnsupportedCommand implements Command {

    private static Logger logger = LoggerFactory.getLogger(UnsupportedCommand.class);

    @Override
    public void execute() {
        logger.debug("Unsupported command");
    }
}
