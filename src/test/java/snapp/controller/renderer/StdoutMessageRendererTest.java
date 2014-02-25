package snapp.controller.renderer;


import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import snapp.controller.message.renderer.StdoutMessageRenderer;
import snapp.domain.Message;
import snapp.domain.User;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.*;

public class StdoutMessageRendererTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private StdoutMessageRenderer renderer;


    @Before
    public void init() {
        renderer = new StdoutMessageRenderer(new PrintStream(outContent));
    }

    @Test
    public void testStdoutMessageRenderer() {

        User u1 = new User();
        u1.setUsername("TestUser");

        Message m1 = new Message();
        m1.setUser(u1);
        m1.setTimestamp(DateTime.now());
        m1.setMessage("test message");

        renderer.render(Lists.newArrayList(m1));

        assertThat(outContent.toString(), startsWith(String.format("%s - %s (", u1.getUsername(), m1.getMessage())));
    }

}
