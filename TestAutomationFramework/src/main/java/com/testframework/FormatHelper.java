package com.testframework;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatHelper {
    public static String formatDateTime(LocalDateTime dateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return currentDateTime.format(formatter);
    }
}
