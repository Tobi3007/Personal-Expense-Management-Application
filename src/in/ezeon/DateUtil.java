package in.ezeon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class contains static methods to handle date operations.
 */
public class DateUtil {
    public static final String[] MONTHS = {
        "January", "February", "March", "April", "May", "June", 
        "July", "August", "September", "October", "November", "December"
    };

    /**
     * Converts a string formatted date to a Date object.
     * @param dateAsString The string formatted date.
     * @return A Date object for the input date string, or null if parsing fails.
     */
    public static Date stringToDate(String dateAsString) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            return df.parse(dateAsString);
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Converts a Date object to a string.
     * @param date The Date object to be converted to a string.
     * @return A string date in "dd/MM/yyyy" format.
     */
    public static String dateToString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(date);
    }

    /**
     * Returns the year and month from a given Date in "yyyy, MM" format.
     * @param date The date from which year and month will be extracted.
     * @return A string representing the year and month.
     */
    public static String getYearAndMonth(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy, MM");
        return df.format(date);
    }

    /**
     * Returns the year for a given Date.
     * @param date The date from which the year will be extracted.
     * @return An integer representing the year.
     */
    public static Integer getYear(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        return Integer.valueOf(df.format(date));
    }

    /**
     * Returns the month name for a given month number.
     * @param monthNo The month number (1 to 12).
     * @return The month name for the input month number.
     */
    public static String getMonthName(Integer monthNo) {
        if (monthNo < 1 || monthNo > 12) {
            throw new IllegalArgumentException("Month number must be between 1 and 12");
        }
        return MONTHS[monthNo - 1];
    }
}
