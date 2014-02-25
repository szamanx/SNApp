package snapp.utility;

import org.joda.time.DateTime;
import org.joda.time.Period;


public class TimeUtil {

    public static String formatPeriod(Period period) {
        StringBuilder builder = new StringBuilder();
        formatPeriodField(period.getYears(), "year", builder);
        formatPeriodField(period.getMonths(), "month", builder);
        formatPeriodField(period.getWeeks(), "week", builder);
        formatPeriodField(period.getDays(), "day", builder);
        formatPeriodField(period.getHours(), "hour", builder);
        formatPeriodField(period.getMinutes(), "minute", builder);
        formatPeriodField(period.getSeconds(), "second", builder);

        if(builder.length() == 0) {
            builder.append("now");
        }

        return builder.toString();
    }

    public static String formatPeriod(DateTime start, DateTime end) {

        Period period = new Period(start, end);

        return formatPeriod(period);
    }

    private static void formatPeriodField(int value, String fieldName, StringBuilder builder) {
        if(builder.length() == 0 && value > 0 ) {
            builder.append(String.format("%s %s%s ago", value, fieldName, value > 1 ? "s" : "" ));
        }
    }
}
