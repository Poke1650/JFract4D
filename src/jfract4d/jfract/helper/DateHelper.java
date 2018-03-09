package jfract4d.jfract.helper;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Antoine Gagnon
 */
public class DateHelper {

    public static final SimpleDateFormat SQL_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Timestamp toTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    public static String toSQLDateTime(Date date) {
        return SQL_DATETIME_FORMAT.format(date);
    }
}
