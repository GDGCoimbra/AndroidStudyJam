package com.gdgcoimbra.justjava;

import java.text.NumberFormat;

public class Utils {

    public static String getPriceWithLocalCurrency(int totalPrice) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        return currencyFormatter.format(totalPrice);
    }
}
