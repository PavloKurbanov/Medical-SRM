package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateTimeFormat {
    private final static DateTimeFormatter FORMATTED = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static String format(LocalDateTime localDateTime) {
        return (localDateTime == null) ? "N/A" : localDateTime.format(FORMATTED);
    }

    public static LocalDateTime parse(String text) throws DateTimeParseException {
        return LocalDateTime.parse(text, FORMATTED);
    }
}