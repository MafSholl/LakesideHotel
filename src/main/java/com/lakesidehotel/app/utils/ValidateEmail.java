package com.lakesidehotel.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateEmail {
    public static boolean isValidEmail(String email){
        String regex = "[a-zA-Z][\\w-]{1,20}@\\w{2,20}\\.\\w{2,3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
