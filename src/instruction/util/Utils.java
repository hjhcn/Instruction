package instruction.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	public static boolean isPhoneNumber(String smsphone) {
		Pattern p = Pattern
				.compile("^1\\d{10}$");
		Matcher m = p.matcher(smsphone);
		return m.matches();
	}

	public static void main(String[] arg) {
		Utils.isPhoneNumber("12888888888");

	}

}
