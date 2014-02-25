package snapp.utility;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.joda.time.Period;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class TimeUtilTest {
    @Test
    @Parameters(method = "parameters")
    public void testFormatPeriod(int years, int months, int weeks, int days, int hours, int minutes, int seconds, String fieldName, boolean plural) throws Exception {
        Period p = new Period(years, months, weeks, days, hours, minutes, seconds, 0);

        assertThat(TimeUtil.formatPeriod(p), is(String.format("%s %s%s ago", (plural ? 2 : 1), fieldName, (plural ? "s" : ""))));
    }

    @Test
    public void testFormatPeriodNow() {
        Period p = new Period();

        assertThat(TimeUtil.formatPeriod(p), is("now"));
    }

    private Object parameters() {
        return $(
                $(1, 0, 0, 0, 0, 0, 0, "year", false),
                $(2, 0, 0, 0, 0, 0, 0, "year", true),
                $(0, 1, 0, 0, 0, 0, 0, "month", false),
                $(0, 2, 0, 0, 0, 0, 0, "month", true),
                $(0, 0, 1, 0, 0, 0, 0, "week", false),
                $(0, 0, 2, 0, 0, 0, 0, "week", true),
                $(0, 0, 0, 1, 0, 0, 0, "day", false),
                $(0, 0, 0, 2, 0, 0, 0, "day", true),
                $(0, 0, 0, 0, 1, 0, 0, "hour", false),
                $(0, 0, 0, 0, 2, 0, 0, "hour", true),
                $(0, 0, 0, 0, 0, 1, 0, "minute", false),
                $(0, 0, 0, 0, 0, 2, 0, "minute", true),
                $(0, 0, 0, 0, 0, 0, 1, "second", false),
                $(0, 0, 0, 0, 0, 0, 2, "second", true)
        );
    }
}
