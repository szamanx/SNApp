package snapp.controller.command.types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import snapp.controller.command.Command;
import snapp.controller.message.renderer.MessageRenderer;
import snapp.domain.Message;
import snapp.service.CommunicationService;

import java.util.List;

public class WallCommand implements Command {
    private static Logger logger = LoggerFactory.getLogger(WallCommand.class);

    private String username;

    private CommunicationService communicationService;

    private MessageRenderer renderer;

    public static Builder builder() {
        return new Builder();
    }

    protected WallCommand(Builder builder) {
        this.username = builder.username;

        this.communicationService = builder.communicationService;
        this.renderer = builder.renderer;
    }

    @Override
    public void execute() {

        List<Message> messages = communicationService.getWall(username);
        logger.debug(String.format("Wall: user %s requested wall", username));
        renderer.render(messages);
    }


    public static class Builder {

        private String username;

        private CommunicationService communicationService;

        private MessageRenderer renderer;

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withCommunicationService(CommunicationService communicationService) {
            this.communicationService = communicationService;
            return this;
        }

        public Builder withRenderer(MessageRenderer renderer) {
            this.renderer = renderer;
            return this;
        }

        public WallCommand build() {
            return new WallCommand(this);
        }

    }
}
