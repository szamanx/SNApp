package snapp.controller.message.renderer;

import snapp.domain.Message;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: daniel
 * Date: 23/02/14
 * Time: 23:39
 * To change this template use File | Settings | File Templates.
 */
public interface MessageRenderer {
    void render(List<Message> messages);
}
