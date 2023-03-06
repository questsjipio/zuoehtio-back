package com.zuoehtio.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexHelper {
    public static final String REGEX_ALPHANUMERIC = "^[a-zA-Z\\d]+$";
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    public static final String REGEX_PERSON_NAME = "^[a-zA-Z\\s-]+$";
    public static final String REGEX_PHONE_NO_SG = "^[3689]\\d{7}$";

    public static boolean validateString(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    private RegexHelper() {
        // Empty
    }
}