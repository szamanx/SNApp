package snapp.controller.message.renderer;

import org.joda.time.DateTime;
import snapp.domain.Message;
import snapp.utility.TimeUtil;

import java.io.PrintStream;
import java.util.List;

public class StdoutMessageRenderer implements MessageRenderer{

    public StdoutMessageRenderer(PrintStream out) {
        this.out = out;
    }

    public StdoutMessageRenderer() {
        this.out = System.out;
    }

    private PrintStream out;

    @Override
    public void render(List<Message> messages) {

        StringBuilder builder = new StringBuilder();
        DateTime now = DateTime.now();
        for(Message message : messages) {

            builder.append(String.format("%s - %s (%s)\n", message.getUser().getUsername(), message.getMessage(), TimeUtil.formatPeriod(message.getTimestamp(), now)));

        }

        out.println(builder.toString());
    }

    public void setOut(PrintStream out) {
        this.out = out;
    }
}

