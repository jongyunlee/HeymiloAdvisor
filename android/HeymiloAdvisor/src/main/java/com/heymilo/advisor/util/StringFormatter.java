package com.heymilo.advisor.util;

import java.util.Currency;
import java.util.Locale;

public class StringFormatter {

    public static String formatPrice(int price) {
	return String.format("%,d원", price);
    }

    public static String formatCharge(int price) {
	if (price == 0) {
	    return "무료배송";
	}
	return String.format("%,d원", price);
    }

    public static String formatPriceWithCurrencyTag(int price) {
	return String.format(Currency.getInstance(Locale.KOREA).getSymbol() + " %,d", price);
    }
}
