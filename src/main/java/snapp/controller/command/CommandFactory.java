package snapp.controller.command;

import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Required;
import snapp.controller.command.types.*;
import snapp.controller.message.renderer.MessageRenderer;
import snapp.domain.User;
import snapp.service.CommunicationService;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: daniel
 * Date: 23/02/14
 * Time: 17:04
 * To change this template use File | Settings | File Templates.
 */
public class CommandFactory {

    private static final UnsupportedCommand UNSUPPORTED_COMMAND = new UnsupportedCommand();

    private Map<CommandType, LineTokenizer> tokenizers;

    private CommunicationService communicationService;

    private MessageRenderer messageRenderer;

    public Command create(String commandText) {

        CommandType cmdType = CommandType.UNSUPPORTED;
        FieldSet fieldSet = null;
        LineTokenizer tokenizer;

        for (Map.Entry<CommandType, LineTokenizer> entry : tokenizers.entrySet()) {
            tokenizer = entry.getValue();

            fieldSet = tokenizer.tokenize(commandText);
            if (fieldSet.getValues().length > 0) {
                cmdType = entry.getKey();
                break;
            }
        }

        User user, userToFollow;
        Command command;
        switch (cmdType) {
            case POSTING:
                command = PostingCommand.builder().withMessage(fieldSet.readString(1)).withUsername(fieldSet.readString(0)).
                        withCommunicationService(communicationService).build();
                break;

            case FOLLOWING:
                command = FollowingCommand.builder().withUsername(fieldSet.readString(0)).withUsernameToFollow(fieldSet.readString(1)).
                        withCommunicationService(communicationService).build();
                break;

            case READING:
                command = ReadingCommand.builder().withUsername(fieldSet.readString(0)).withCommunicationService(communicationService).
                        withRenderer(messageRenderer).build();
                break;

            case WALL:
                command = WallCommand.builder().withUsername(fieldSet.readString(0)).withCommunicationService(communicationService).
                        withRenderer(messageRenderer).build();
                break;

            default:
                command = UNSUPPORTED_COMMAND;
        }

        return command;
    }

    @Required
    public void setCommunicationService(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    @Required
    public void setTokenizers(Map<CommandType, LineTokenizer> tokenizers) {
        this.tokenizers = tokenizers;
    }

    @Required
    public void setMessageRenderer(MessageRenderer messageRenderer) {
        this.messageRenderer = messageRenderer;
    }
}
