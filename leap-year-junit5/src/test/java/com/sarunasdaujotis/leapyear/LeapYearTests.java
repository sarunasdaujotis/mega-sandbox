package com.sarunasdaujotis.leapyear;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LeapYearTests {

	@Nested
	class A_year_is_not_supported {
		@ParameterizedTest
		@ValueSource(ints = {Integer.MIN_VALUE, -8, -4, -1})
		void if_it_is_negative(int year) {
			assertThrows(IllegalArgumentException.class, () -> Year.isLeap(year));
		}

		@Test
		void if_it_is_zero() {
			assertThrows(IllegalArgumentException.class, () -> Year.isLeap(0));
		}
	}

	@Nested
	class A_year_is_supported {
		@ParameterizedTest
		@ValueSource(ints = {1, 4, Integer.MAX_VALUE})
		void if_it_is_larger_then_zero(int year) {
			assertDoesNotThrow(() -> Year.isLeap(year));
		}
	}

	@Nested
	class A_year_is_a_leap {
		@ParameterizedTest
		@ValueSource(ints = {2020, 2016, 1984, 4})
		void if_it_divisible_by_4_but_not_by_100(int year) {
			assertTrue(Year.isLeap(year));
		}

		@ParameterizedTest
		@ValueSource(ints = {2400, 2000, 1600, 400})
		void if_it_divisible_by_400(int year) {
			assertTrue(Year.isLeap(year));
		}
	}

	@Nested
	class A_year_is_not_a_leap {
		@ParameterizedTest
		@ValueSource(ints = {2021, 2018, 1999, 1})
		void if_it_is_not_divisible_by_4(int year) {
			assertFalse(Year.isLeap(year));
		}

		@ParameterizedTest
		@ValueSource(ints = {2100, 1900, 100})
		void if_it_is_divisible_by_100_but_not_by_400(int year) {
			assertFalse(Year.isLeap(year));
		}
	}
}