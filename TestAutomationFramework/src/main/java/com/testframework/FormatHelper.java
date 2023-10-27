package com.testframework;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatHelper {

    /**
     * Formats a LocalDateTime object into a string representation using the specified format.
     *
     * @param dateTime the LocalDateTime object to be formatted
     * @param format the format to be used
     * @return the formatted string
     */
    public static String formatDateTime(LocalDateTime dateTime, String format) {
        if (dateTime == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return dateTime.format(formatter);
    }

    /**
     * Parses a string representation of a date and time into a LocalDateTime object using the specified format.
     *
     * @param dateTime the string representation of the date and time
     * @param format the format used
     * @return the parsed LocalDateTime object
     */
    public static LocalDateTime parseDateTimeFromString(String dateTime, String format) {
        if (dateTime == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Formats a LocalDate object into a string representation using the specified format.
     *
     * @param date the LocalDate object to be formatted
     * @param format the format to be used
     * @return the formatted string
     */
    public static String formatDate(LocalDate date, String format) {
        if (date == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    /**
     * Parses a string representation of a date into a LocalDate object using the specified format.
     *
     * @param dateTime the string representation of the date and time
     * @param format the format used
     * @return the parsed LocalDate object
     */
    public static LocalDate parseDateFromString(String dateTime, String format) {
        if (dateTime == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(dateTime, formatter);
    }

    /**
     * Extracts the first number found in the provided text.
     *
     * @param text the text from which to extract the number
     * @return the extracted number, or -1 if no number is found
     */
    public static int extractNumber(String text) {
        try {
            Pattern pattern = Pattern.compile("\\b(\\d+)\\b");
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                String numberString = matcher.group(1);
                return Integer.parseInt(numberString);
            }
        } catch (NumberFormatException | IllegalStateException | IndexOutOfBoundsException | NullPointerException e) {
            Utils.LOGGER.info(e);
        }
        return -1;
    }
}
