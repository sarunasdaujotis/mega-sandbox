package com.sarunasdaujotis.leapyear;

public final class Year {
	private Year() {
	}

	public static boolean isLeap(final int year) {
		if (year <= 0 ) {
			throw new IllegalArgumentException();
		}
		return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
	}
}