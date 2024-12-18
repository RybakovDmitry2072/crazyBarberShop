package org.example.crazybarbershop.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@UtilityClass
public class LocalDateTimeFormatter {

    private static final String PATTERN = "dd.MM.yyyy HH:mm";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public static LocalDateTime format(String dateTime) {
        return LocalDateTime.parse(dateTime, FORMATTER);
    }

    public static boolean isValid(String dateTime) {
        try {
            return Optional.ofNullable(dateTime)
                    .map(LocalDateTimeFormatter::format)
                    .isPresent();
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static String formatToString(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }
}
