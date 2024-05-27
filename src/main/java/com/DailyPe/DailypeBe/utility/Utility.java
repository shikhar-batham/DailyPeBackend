package com.DailyPe.DailypeBe.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    private static final String PAN_PATTERN = "[A-Z]{5}[0-9]{4}[A-Z]{1}";

    public static boolean validateMobileNumber(String number) {

        int numberLength = number.length();
        if (numberLength < 10) return false;

        if (number.charAt(0) == '+') {
            String firstHalf = number.substring(0, 3);
            String secondHalf = number.substring(3);
            if (!firstHalf.equals("+91")) return false;
            return secondHalf.length() >= 10;
        } else if (number.charAt(0) == '0') {
            String secHalf = number.substring(1);
            return secHalf.length() >= 10;
        } else {
            return number.length() == 10;
        }
    }

    public static boolean isValidPAN(String pan) {

        pan = pan.toUpperCase();

        Pattern pattern = Pattern.compile(PAN_PATTERN);

        Matcher matcher = pattern.matcher(pan);

        return matcher.matches();
    }

    public static String validateAndConvertPAN(String pan) {

        String upperCasePAN = pan.toUpperCase();

        Pattern pattern = Pattern.compile(PAN_PATTERN);

        Matcher matcher = pattern.matcher(upperCasePAN);

        if (matcher.matches()) {
            return upperCasePAN;
        } else {
            return "Invalid PAN number";
        }
    }
}
