public class Date {

	private static final int FIRST_YEAR = 1900;
	private static final int FIRST_MONTH = 1;
	private static final int LAST_MONTH = 12;
	private static final int FIRST_DAY = 1;

	private static final int[] DAYS_IN_MONTHS_NON_LEAP_YEAR = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static final int[] DAYS_IN_MONTHS_LEAP_YEAR = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	private int year, month, day;

	public Date(int year, int month, int day) {
		if (year < FIRST_YEAR)
			throw new IllegalArgumentException("Too early year %d, first possible is %d".formatted(year, FIRST_YEAR));
		this.year = year;

		if (month < FIRST_MONTH || month > LAST_MONTH)
			throw new IllegalArgumentException("Wrong month %d".formatted(month));
		this.month = month;

		if (day < FIRST_DAY || day > daysInMonth(year, month))
			throw new IllegalArgumentException("Wrong day %d".formatted(day));
		this.day = day;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public int getDayInYear() {
		int days = day;
		for (int m = FIRST_MONTH; m < month; m++)
			days += daysInMonth(year, m);
		return days;
	}

	private static boolean isLeapYear(int year) {
		return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
	}

	private static int daysInMonth(int year, int month) {
		int[] days = isLeapYear(year) ? DAYS_IN_MONTHS_LEAP_YEAR : DAYS_IN_MONTHS_NON_LEAP_YEAR;
		return days[month - 1];
	}

	@Override
	public String toString() {
		return "%04d-%02d-%02d".formatted(year, month, day);
	}

}

//Comment sjhd

