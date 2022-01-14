package duong.dev.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class Roandom {
	private static final String alpha = "abcdefghijklmnopqrstuvwxyz";
	private static final String alphaUpperCase = alpha.toUpperCase();
	private static final String digits = "0123456789";
	private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
	private static final String ALL = alpha + alphaUpperCase + digits ;
	private static Random generator = new Random();
	
	public static String randomNumberAndString() {
		List<String> result = new ArrayList<>();
		Consumer<String> appendChar = s -> {
			int number = randomNumber(0, s.length() - 1);
			result.add("" + s.charAt(number));
		};
		appendChar.accept(digits);
		while (result.size() < 30) {
			appendChar.accept(ALL);
		}
		Collections.shuffle(result, generator);
		return String.join("", result);
	}

	public static int randomNumber(int min, int max) {
		return generator.nextInt((max - min) + 1) + min;
	}

	public static String getAlphaNumeric() {
		return ALPHA_NUMERIC;
	}
}
