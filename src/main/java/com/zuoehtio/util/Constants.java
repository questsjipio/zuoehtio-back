package com.zuoehtio.util;

import java.time.format.DateTimeFormatter;

public class Constants {
    public static final String TIMEZONE_SG = "Asia/Singapore";
    public static final DateTimeFormatter DATEFORMAT_WITHTIMEZONE = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
    public static final String COMPANY_ONBEHALF_YES = "1";
    public static final String COMPANY_ONBEHALF_NO = "0";
    public static final String LINE_SEPARATOR = "line.separator";
    public static final String STRING_DELIMITER = ",";

    private Constants() {
        // Private Constructor
    }
}