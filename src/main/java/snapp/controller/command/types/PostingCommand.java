package snapp.controller.command.types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import snapp.controller.command.Command;

import snapp.service.CommunicationService;

public class PostingCommand implements Command {

    private static Logger logger = LoggerFactory.getLogger(PostingCommand.class);

    private String message;

    private String username;

    private CommunicationService communicationService;

    public static Builder builder() {
        return new Builder();
    }

    protected PostingCommand(Builder builder) {
        this.message = builder.message;
        this.username = builder.username;

        this.communicationService = builder.communicationService;
    }

    @Override
    public void execute() {
        communicationService.post(username, message);
        logger.debug(String.format("User %s posted message '%s'", username, message));
    }


    public static class Builder {

        private String message;

        private String username;

        private CommunicationService communicationService;

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withCommunicationService(CommunicationService communicationService) {
            this.communicationService = communicationService;
            return this;
        }

        public PostingCommand build() {
            return new PostingCommand(this);
        }
    }
}
