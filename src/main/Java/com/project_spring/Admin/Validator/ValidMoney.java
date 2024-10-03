package com.project_spring.Admin.Validator;

import java.text.DecimalFormat;

public class ValidMoney {

    public static boolean isValid(String strMoney) {
        for(int i = 0; i < strMoney.length(); ++i) {
            if(!Character.isDigit(strMoney.charAt(i)))
                return false;
        }
        return true;
    }

    public static boolean isValid(double money) {
        String strMoney = String.valueOf(money);
        for(int i = 0; i < strMoney.length(); ++i) {
            if(!Character.isDigit(strMoney.charAt(i)))
                return false;
        }
        return true;
    }

    public static boolean isValid(int money) {
        String strMoney = String.valueOf(money);
        for(int i = 0; i < strMoney.length(); ++i) {
            if(!Character.isDigit(strMoney.charAt(i)))
                return false;
        }
        return true;
    }

    public static double formatMoney(double money) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        String formattedMoney = decimalFormat.format(money);
        return isValid(formattedMoney) ? Double.valueOf(formattedMoney) : 0.0;
    }

    public static int formatMoney(int money) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedMoney = decimalFormat.format(money);
        return isValid(formattedMoney) ? Integer.valueOf(formattedMoney) : 0;
    }
}
