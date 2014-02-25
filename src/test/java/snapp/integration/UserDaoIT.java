package snapp.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import snapp.domain.User;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-dl-data-context.xml")
public class UserDaoIT {

    @Autowired
    private UserDao userDao;

    @Test
    public void testFindExistingUser() throws Exception {
        User u3 = userDao.findOne("User3");

        assertThat(u3, is(notNullValue()));
        assertThat(u3.getUsername(), is("User3"));
    }

    @Test
    public void testFindNonExistingUser() throws Exception {
        User noExisting = userDao.findOne("NonExistingUser");

        assertThat(noExisting, is(nullValue()));
    }


}
