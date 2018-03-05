package jfract4d.jfract.helper;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Antoine Gagnon
 */
public class DateHelper {

    public static Timestamp toTimestamp(Date date) {
        return new Timestamp(date.getTime());
    } 
}
