package org.example.crazybarbershop.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

//TODO : доделать
public class LocalDateTimeFormatter {
    private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public static LocalDateTime format(String date) {
        return LocalDateTime.parse(date, FORMATTER);
    }
    public static String extractDateTimeWithoutSeconds(String dateTimeString) {
        try {
            // Парсим строку в LocalDateTime
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, FORMATTER);
            // Форматируем LocalDateTime в строку, содержащую только дату и время без секунд
            return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            // Обработка ошибки парсинга
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isValid(String date) {
        try {
            return Optional.ofNullable(date)
                    .map(LocalDateTimeFormatter::format)
                    .isPresent();
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}

