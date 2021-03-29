package com.sarunasdaujotis.numberclosetozero;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class NumberCloseToZeroTests {

	@Nested
	class Valid_scenarios {
		@ParameterizedTest(name = "Input array {0}; expected {1}")
		@CsvFileSource(resources = "/input-positive-numbers.csv", delimiterString = ";")
		void if_array_contains_only_positive_numbers(@ConvertWith(IntArrayConverter.class) int[] input,
				@ConvertWith(IntConverter.class) int expected) {
			Assertions.assertEquals(expected, Closest.toZero(input));
		}

		@ParameterizedTest(name = "Input array {0}; expected {1}")
		@CsvFileSource(resources = "/input-mixed-numbers.csv", delimiterString = ";")
		void if_array_contains_mixed_numbers(@ConvertWith(IntArrayConverter.class) int[] input,
				@ConvertWith(IntConverter.class) int expected) {
			Assertions.assertEquals(expected, Closest.toZero(input));
		}

		@ParameterizedTest(name = "Input array {0}; expected {1}")
		@CsvFileSource(resources = "/input-negative-numbers.csv", delimiterString = ";")
		void if_array_contains_only_negative_numbers(@ConvertWith(IntArrayConverter.class) int[] input,
				@ConvertWith(IntConverter.class) int expected) {
			Assertions.assertEquals(expected, Closest.toZero(input));
		}
	}

	@Nested
	class Invalid_scenarios {
		@ParameterizedTest
		@NullAndEmptySource
		void if_it_is_an_empty_array(int[] array) {
			Assertions.assertThrows(IllegalArgumentException.class, () -> Closest.toZero(array));
		}

	}

	static class IntArrayConverter implements ArgumentConverter {

		@Override
		public Object convert(Object source, ParameterContext context)
				throws ArgumentConversionException {
			if (!(source instanceof String)) {
				throw new IllegalArgumentException("The argument should be a string: " + source);
			}
			try {
				return Arrays.stream(((String) source).split(",")).mapToInt(Integer::parseInt).toArray();
			} catch (Exception e) {
				e.printStackTrace();
				throw new IllegalArgumentException("Failed to convert", e);
			}
		}
	}

	static class IntConverter implements ArgumentConverter {

		@Override
		public Object convert(final Object source, final ParameterContext context) throws ArgumentConversionException {
			return Integer.parseInt((String) source);
		}
	}
}
