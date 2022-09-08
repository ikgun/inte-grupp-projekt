import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

class DateTest {

	@Test
	void legalDate_setsAttributes() {
		Date d = new Date(2000, 1, 31);

		assertEquals(2000, d.getYear(), "Wrong year");
		assertEquals(1, d.getMonth(), "Wrong month");
		assertEquals(31, d.getDay(), "Wrong day");
	}

	@Test
	void tooLowYear_throwsException() {
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
			new Date(1800, 1, 1);
		});
		assertEquals("Too early year 1800, first possible is 1900", e.getMessage());
	}

	@Test
	void tooLowMonth_throwsException() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Date(2000, 0, 1);
		});
	}

	@Test
	void tooHighMonth_throwsException() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Date(2000, 13, 1);
		});
	}

	@Test
	void tooLowDay_throwsException() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Date(2000, 1, 0);
		});
	}

	@ParameterizedTest(name = "{index} month {0} does not have {1} days")
	@CsvSource(value = { "1,32", "4,31", "6,31", "7,32" })
	void tooHighDay_throwsException(int m, int d) {
		assertThrows(IllegalArgumentException.class, () -> {
			new Date(2000, m, d);
		});
	}

	@ParameterizedTest
	@ValueSource(ints = { 1900, 2100, 1999, 2001, 2021, 2022, 2023 })
	void february_have28Days_nonLeapYears(int y) {
		assertDoesNotThrow(() -> {
			new Date(y, 2, 28);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Date(y, 2, 29);
		});
	}

	@ParameterizedTest
	@ValueSource(ints = { 1996, 2000, 2004, 2020, 2024 })
	void february_have29Days_leapYears(int y) {
		assertDoesNotThrow(() -> {
			new Date(y, 2, 29);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Date(y, 2, 30);
		});
	}

	@Test
	void singleDigitMonthAndDay_arePaddedWithZeros_inToString() {
		Date d = new Date(1900, 1, 2);
		assertEquals("1900-01-02", d.toString());
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3, 4, 5 })
	void firstOfJanuary_isDayOneInYear(int day) {
		Date d = new Date(1900, 1, day);
		assertEquals(day, d.getDayInYear());
	}

	@ParameterizedTest(name = "{index} month {0} day {1} is day {2} of the year")
	@CsvSource(value = { "1,1,1", "4,5,95", "12,31,365" })
	void dayOfYearCalculatedCorrectly_nonLeapYear(int month, int day, int days) {
		Date d = new Date(1900, month, day);
		assertEquals(days, d.getDayInYear());
	}

	@ParameterizedTest(name = "{index} month {0} day {1} is day {2} of the year")
	@CsvSource(value = { "1,1,1", "4,5,96", "12,31,366" })
	void dayOfYearCalculatedCorrectly_leapYear(int month, int day, int days) {
		Date d = new Date(2000, month, day);
		assertEquals(days, d.getDayInYear());
	}
}
