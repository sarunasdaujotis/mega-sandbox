package com.sarunasdaujotis.numberclosetozero;

public class Closest {
	public static int toZero(final int... input) {
		if (input == null || input.length == 0) {
			throw new IllegalArgumentException();
		}

		var closest = input[0];
		for (final int number : input) {
			var abs = Math.abs(number);
			var absClosest = Math.abs(closest);
			if (abs < absClosest
					|| (abs == absClosest && closest < 0)) {
				closest = number;
			}
		}

		return closest;
	}
}