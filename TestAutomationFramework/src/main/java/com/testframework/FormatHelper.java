package com.testframework;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatHelper {

    public static String formatDateTime(LocalDateTime dateTime) {
        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime utcDateTime = zonedDateTime.withZoneSameInstant(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return utcDateTime.format(formatter);
    }

    public static String formatBirthdayDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    public static int extractNumber(String text) {
        try {
            Pattern pattern = Pattern.compile("\\b(\\d+)\\b");
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                String numberString = matcher.group(1);
                return Integer.parseInt(numberString);
            }
        } catch (NumberFormatException | IllegalStateException | IndexOutOfBoundsException | NullPointerException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
