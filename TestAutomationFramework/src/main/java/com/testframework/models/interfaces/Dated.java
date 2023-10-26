package com.testframework.models.interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Interface providing methods for comparing dates and date-times approximately.
 */
public interface Dated {

    /**
     * Checks if two LocalDateTime objects are approximately equal within the specified threshold in seconds.
     *
     * @param dt1              the first LocalDateTime object
     * @param dt2              the second LocalDateTime object
     * @param thresholdSeconds the threshold in seconds
     * @return true if the difference is within the threshold, false otherwise
     */
    static boolean dateTimeApproximatelyEquals(LocalDateTime dt1, LocalDateTime dt2, long thresholdSeconds) {
        if (dt1 == null && dt2 == null) return true;
        long diff = Math.abs(ChronoUnit.SECONDS.between(dt1, dt2));
        return diff <= thresholdSeconds;
    }

    /**
     * Checks if two LocalDate objects are approximately equal within the specified threshold in days.
     *
     * @param d1            the first LocalDate object
     * @param d2            the second LocalDate object
     * @return true if the difference is within the threshold, false otherwise
     */
    static boolean dateApproximatelyEquals(LocalDate d1, LocalDate d2) {
        if (d1 == null && d2 == null) return true;
        long diff = Math.abs(ChronoUnit.DAYS.between(d1, d2));
        return diff <= 1;
    }

}
