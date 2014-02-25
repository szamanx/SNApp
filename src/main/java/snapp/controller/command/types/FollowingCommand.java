package snapp.controller.command.types;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import snapp.controller.command.Command;
import snapp.service.CommunicationService;

public class FollowingCommand implements Command {
    private static Logger logger = LoggerFactory.getLogger(FollowingCommand.class);

    private String usernameToFollow;

    private String username;

    private CommunicationService communicationService;

    public static Builder builder() {
        return new Builder();
    }

    protected FollowingCommand(Builder builder) {
        this.usernameToFollow = builder.usernameToFollow;
        this.username = builder.username;

        this.communicationService = builder.communicationService;
    }

    @Override
    public void execute() {
        communicationService.follow(username, usernameToFollow);
        logger.debug(String.format("User %s follows username '%s'", username, usernameToFollow));
    }


    public static class Builder {

        private String usernameToFollow;

        private String username;

        private CommunicationService communicationService;

        public Builder withUsernameToFollow(String usernameToFollow) {
            this.usernameToFollow = usernameToFollow;
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

        public FollowingCommand build() {
            return new FollowingCommand(this);
        }
    }
}
