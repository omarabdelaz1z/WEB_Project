package Utils;

import java.util.Calendar;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.text.WordUtils;

public class CalendarUtils {
    private final Calendar calendar;
    private final List<String> DAY_OF_WEEK;

    public CalendarUtils() {
        calendar = Calendar.getInstance();
        DAY_OF_WEEK = Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
    }

    public static void main(String[] args) {
        CalendarUtils calendarUtils = new CalendarUtils();
        System.out.println(calendarUtils.getDayOfWeek(7));
        System.out.println(calendarUtils.getDayOfWeek("sUnday"));
        System.out.println(calendarUtils.getCurrentDay());

    }

    public String getDayOfWeek(int DAY_OF_WEEK) {
        try {
            return this.DAY_OF_WEEK.get(DAY_OF_WEEK - 1);
        } catch (Exception e) {
            System.out.println("Day not found.");
            return null;
        }
    }

    public int getDayOfWeek(String day) {
        return DAY_OF_WEEK.indexOf(WordUtils.capitalizeFully(day)) + 1;
    }

    public String getCurrentDay() {
        return DAY_OF_WEEK.get(calendar.get(Calendar.DAY_OF_WEEK) - 1);
    }
}
