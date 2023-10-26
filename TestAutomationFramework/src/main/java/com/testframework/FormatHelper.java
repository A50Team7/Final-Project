package com.testframework;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatHelper {

    /**
     * Formats a LocalDateTime object into a string representation using the "dd/MM/yyyy HH:mm:ss" format.
     *
     * @param dateTime the LocalDateTime object to be formatted
     * @return the formatted string
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }

    /**
     * Parses a string representation of a date and time into a LocalDateTime object using the "dd/MM/yyyy HH:mm:ss" format.
     *
     * @param dateTime the string representation of the date and time
     * @return the parsed LocalDateTime object
     */
    public static LocalDateTime parseDateTimeFromString(String dateTime) {
        if (dateTime == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Formats a LocalDate object into a string representation using the "yyyy-MM-dd" format.
     *
     * @param date the LocalDate object to be formatted
     * @return the formatted string
     */
    public static String formatBirthdayDate(LocalDate date) {
        if (date == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    /**
     * Parses a string representation of a date into a LocalDate object using the "yyyy-MM-dd" format.
     *
     * @param dateTime the string representation of the date and time
     * @return the parsed LocalDate object
     */
    public static LocalDate parseDateFromString(String dateTime) {
        if (dateTime == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateTime, formatter);
    }

    /**
     * Formats a LocalDate object into a string representation using the "dd/MM/yyyy" format.
     *
     * @param date the LocalDate object to be formatted
     * @return the formatted string
     */
    public static String formatRegistrationDate(LocalDate date) {
        if (date == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    /**
     * Formats a LocalDate object into a string representation using the "MM/dd/yyyy" format.
     *
     * @param date the LocalDate object to be formatted
     * @return the formatted string
     */
    public static String formatDateAmericanFormat(LocalDate date) {
        if (date == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return date.format(formatter);
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
