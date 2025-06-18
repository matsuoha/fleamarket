package util;

import java.text.NumberFormat;
import java.util.Locale;

//金額フォーマット
public class MyFormat {

	public String moneyFormat(int price) {

		NumberFormat curFormat = NumberFormat.getCurrencyInstance(Locale.JAPAN);

		String formattedPrice = curFormat.format(price);

		return formattedPrice;

	}
}