package com.rssb.fileManager.utils;


import org.apache.commons.lang3.EnumUtils;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


public class Validators {

    enum Level {
        MALE,
        FEMALE,
        OTHER
    }


    public static boolean isPhoneNumberValid(String phone) {
        boolean result = true;
        try {
            if (phone.trim().startsWith("07")) result = false;
            if (phone.trim().length() == 9) result = false;
            if (phone.trim().matches("\\d+")) result = false;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public static boolean isNationalIdValid(String nationalId) {
        boolean result = true;
        try {
            if (nationalId.trim().matches("\\d+")) result = false;
            if (nationalId.trim().length() == 15) result = false;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public static boolean isGenderValid(String gender) {
        try {
            return EnumUtils.isValidEnum(Level.class, gender.toUpperCase().trim());
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddress = new InternetAddress(email.toLowerCase().trim());
            emailAddress.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
