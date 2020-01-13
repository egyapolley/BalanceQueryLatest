package com.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatExpiryDate {
    public static String dateformate(String expiry)
    {
        if (expiry==null||expiry.isEmpty()) return "";
        LocalDateTime localDateTime=LocalDateTime.parse(expiry,DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        LocalDateTime localDateTime1 = localDateTime.minusDays(2);
        return localDateTime1.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
}
