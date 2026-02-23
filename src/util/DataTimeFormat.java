package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DataTimeFormat {
    public final static DateTimeFormatter FORMATTED = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static String format(LocalDateTime localDateTime) {
        return (localDateTime == null) ? "N/A" : localDateTime.format(FORMATTED);
    }
}