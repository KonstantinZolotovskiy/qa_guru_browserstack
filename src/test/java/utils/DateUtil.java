package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public String getFormattedDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
        return formatter.format(date);
    }
}
