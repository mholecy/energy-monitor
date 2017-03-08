package holecym.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Michal on 6. 3. 2017.
 */
public final class DateUtils {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private DateUtils() {
    }

    public static String formatDateTime(Date date) {
        final LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return localDateTime.format(formatter);
    }

    public static Date getDateFromString(String stringDate) {
        final LocalDateTime localDateTime = LocalDateTime.parse(stringDate, formatter);
        return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
    }

    public static Date getDateFromTimestamp(Timestamp timestamp) {
        return new Date(timestamp.getTime());
    }
}
