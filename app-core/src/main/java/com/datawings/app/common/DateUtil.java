package com.datawings.app.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

public final class DateUtil {

	public static String date2String(Date sDate, String formatDate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
			return sdf.format(sDate);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date string2Date(String sDate, String formatDate,
			Locale locale) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formatDate, locale);
			sdf.setLenient(false);
			return sdf.parse(sDate);
		} catch (Exception ex) {
			return null;
		}
	}

	public static Date string2Date(String sDate, String formatDate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
			// pour valider la date
			sdf.setLenient(false);
			return sdf.parse(sDate);
		} catch (Exception ex) {
			// ex.printStackTrace();
			return null;
		}
	}

	public static boolean checkDateAsString(String date, String formatDate) {
		boolean bool = (string2Date(date, formatDate) != null) ? true : false;
		return bool;
	}

	@SuppressWarnings("deprecation")
	public static Date dateDateTime(String strDate, int hours, int minutes,
			String formatDateTmp, String formatDateResult) {
		try {
			Date tmp = string2Date(strDate, formatDateTmp);
			if (tmp != null) {
				tmp.setHours(hours);
				tmp.setMinutes(minutes);
				DateFormat dfResult = new SimpleDateFormat(formatDateResult);
				return dfResult.parse(dfResult.format(tmp));
			} else {
				return null;
			}

		} catch (Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public static String stringDateTime(String strDate, int hours, int minutes,
			String formatDateTmp, String formatDateResult) {
		try {
			Date tmp = string2Date(strDate, formatDateTmp);
			if (tmp != null) {
				tmp.setHours(hours);
				tmp.setMinutes(minutes);
				DateFormat dfResult = new SimpleDateFormat(formatDateResult);
				return dfResult.format(tmp);
			} else {
				return null;
			}

		} catch (Exception ex) {
			return null;
		}

	}

	public static Date getDateByYMW(int y, int m, int w) {
		try {

			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, y);
			cal.set(Calendar.MONTH, m - 1);
			cal.set(Calendar.WEEK_OF_MONTH, w);
			cal.set(Calendar.DAY_OF_WEEK, 1);
			return cal.getTime();

		} catch (Exception ex) {
			return null;
		}
	}

	public static Integer getWeek(Date date) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.get(Calendar.WEEK_OF_MONTH);

		} catch (Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public static Integer getHour(Date date) {
		try {
			Integer h = date.getHours();
			return h;

		} catch (Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public static Integer getMinute(Date date) {
		try {
			Integer m = date.getMinutes();
			return m;

		} catch (Exception ex) {
			return null;
		}
	}

	public static String[] getTime(Integer h, Integer m) {
		try {
			String[] rs = { "", "" };
			String ap = "";
			String t = "";
			if (h < 12) {
				ap = "AM";
				if (h < 10) {
					if (h == 0) {
						if (m == 0) {
							ap = "PM";
							t = "0" + h.toString().trim() + ":" + "0"
									+ m.toString().trim();
						} else {
							if (m < 10) {
								t = "0" + h.toString().trim() + ":" + "0"
										+ m.toString().trim();
							} else {
								t = "0" + h.toString().trim() + ":"
										+ m.toString().trim();
							}
						}
					} else {
						if (m < 10) {
							t = "0" + h.toString().trim() + ":" + "0"
									+ m.toString().trim();
						} else {
							t = "0" + h.toString().trim() + ":"
									+ m.toString().trim();
						}
					}

				} else {
					if (m < 10) {
						t = h.toString().trim() + ":" + "0"
								+ m.toString().trim();
					} else {
						t = h.toString().trim() + ":" + m.toString().trim();
					}
				}
			} else {
				if (h == 12) {
					if (m == 0) {
						ap = "AM";
						t = h.toString().trim() + ":" + "0"
								+ m.toString().trim();
					} else {
						ap = "PM";
						h = h - 12;
						t = "0" + h.toString().trim() + ":" + "0"
								+ m.toString().trim();
					}

				} else {
					ap = "PM";
					h = h - 12;

					if (h < 10) {
						if (m < 10) {
							t = "0" + h.toString().trim() + ":" + "0"
									+ m.toString().trim();
						} else {
							t = "0" + h.toString().trim() + ":"
									+ m.toString().trim();
						}
					} else {
						if (m < 10) {
							t = h.toString().trim() + ":" + "0"
									+ m.toString().trim();
						} else {
							t = h.toString().trim() + ":" + m.toString().trim();
						}
					}
				}
			}

			rs[0] = ap;
			rs[1] = t;

			return rs;
		} catch (Exception ex) {
			return null;
		}
	}

	public static boolean isLeapYear(int year) {
		GregorianCalendar calendar = new GregorianCalendar();
		return calendar.isLeapYear(year);
	}

	public static boolean isMonth31(int month) {
		return (month == 1 || month == 3 || month == 5 || month == 7
				|| month == 8 || month == 10 || month == 12);
	}

	public static boolean isMonth30(int month) {
		return (month == 4 || month == 6 || month == 9 || month == 11);
	}

	public static Integer getDaysOfMonth(int month, int year) {
		Integer days = 28;
		if (DateUtil.isMonth31(month)) {
			days = 31;
		} else if (DateUtil.isMonth30(month)) {
			days = 30;
		} else if (month == 2 && DateUtil.isLeapYear(year)) {
			days = 29;
		}
		return days;
	}

	public static List<LocalDate> getWeekendDates(LocalDate start, LocalDate end) {
		List<LocalDate> result = new ArrayList<LocalDate>();
		for (LocalDate date = start; (date.isBefore(end) || date.isEqual(end)); date = date
				.plusDays(1)) {
			int day = date.getDayOfWeek();
			// These could be passed in...
			if (day == DateTimeConstants.SATURDAY
					|| day == DateTimeConstants.SUNDAY) {
				result.add(date);
			}
		}
		return result;
	}

	public static List<LocalDate> getWorkingDates(LocalDate start, LocalDate end) {
		List<LocalDate> result = new ArrayList<LocalDate>();
		for (LocalDate date = start; (date.isBefore(end) || date.isEqual(end)); date = date
				.plusDays(1)) {
			int day = date.getDayOfWeek();
			// These could be passed in...
			if (day != DateTimeConstants.SATURDAY
					&& day != DateTimeConstants.SUNDAY) {
				result.add(date);
			}
		}
		return result;
	}

	public static List<LocalDate> getDatesBetween2Dates(LocalDate start,
			LocalDate end) {
		List<LocalDate> result = new ArrayList<LocalDate>();
		for (LocalDate date = start; (date.isBefore(end) || date.isEqual(end)); date = date
				.plusDays(1)) {
			result.add(date);
		}
		return result;
	}

	public static boolean checAfter(Date dateStart, Date dateEnd) {
		boolean ret = false;
		if (dateEnd.after(dateStart) || equalByDate(dateStart, dateEnd))
			ret = true;
		return ret;
	}

	public static boolean equalByDate(Date date1, Date date2) {
		boolean ret = false;
		Calendar d1 = Calendar.getInstance();
		Calendar d2 = Calendar.getInstance();
		d1.setTime(date1);
		d2.setTime(date2);
		if (d1.get(Calendar.YEAR) == d2.get(Calendar.YEAR)
				&& d1.get(Calendar.MONTH) == d2.get(Calendar.MONTH)
				&& d1.get(Calendar.DAY_OF_MONTH) == d2
						.get(Calendar.DAY_OF_MONTH)
				&& d1.get(Calendar.HOUR) == d2.get(Calendar.HOUR)
				&& d1.get(Calendar.MINUTE) == d2.get(Calendar.MINUTE)) {
			ret = true;
		}
		return ret;
	}

	public static void addDiffDate(List<LocalDate> list, LocalDate date) {
		boolean chk = true;
		for (LocalDate elm : list) {
			if (date.equals(elm)) {
				chk = false;
				break;
			}
		}
		if (chk) {
			list.add(date);
		}
	}

	/**
	 * convert string month(France) to string numberic Ex:result -> ""
	 */
	public static String stringMonthToNum(String month) {
		if (month.equalsIgnoreCase("January")) {
			return "01";
		}
		if (month.equalsIgnoreCase("Fevrier")) {
			return "02";
		}
		if (month.equalsIgnoreCase("Mars")) {
			return "03";
		}
		if (month.equalsIgnoreCase("Avril")) {
			return "04";
		}
		if (month.equalsIgnoreCase("Mai")) {
			return "05";
		}
		if (month.equalsIgnoreCase("Juin")) {
			return "06";
		}
		if (month.equalsIgnoreCase("Juillet")) {
			return "07";
		}
		if (month.equalsIgnoreCase("Aout")) {
			return "08";
		}
		if (month.equalsIgnoreCase("Septembre")) {
			return "09";
		}
		if (month.equalsIgnoreCase("Octobre")) {
			return "10";
		}
		if (month.equalsIgnoreCase("Novembre")) {
			return "11";
		}
		if (month.equalsIgnoreCase("Decembre")) {
			return "12";
		}
		return "";

	}

	public static Date getDateBefore6Month(Date curDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(curDate);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.clear();
		int mm = Integer.parseInt(DateUtil.date2String(curDate, "MM"));
		if (mm - 6 + 1 > 0) {
			calendar2.set(calendar.get(Calendar.YEAR),
					calendar.get(Calendar.MONTH) - 6 + 1, 1);
		} else {
			calendar2.set(calendar.get(Calendar.YEAR) - 1,
					calendar.get(Calendar.MONTH) - 6 + 1 + 12, 1);
		}
		return calendar2.getTime();
	}

	public static Date changeYear(Date date, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.clear();
		calendar2.set(calendar.get(Calendar.YEAR) + year,
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));
		return calendar2.getTime();
	}

	public static List<Date> getList6MonthFrom(Date crrDate) {
		List<Date> rs = new ArrayList<Date>();
		rs.add(crrDate);
		Date elm = crrDate;
		for (int i = 0; i < 5; i++) {
			elm = getNetMonth(elm);
			rs.add(elm);
		}
		return rs;
	}

	public static Date getNetMonth(Date dais) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dais);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.clear();
		if (calendar.get(Calendar.MONTH) == 12) {
			calendar2.set(calendar.get(Calendar.YEAR) + 1,
					calendar.get(Calendar.MONTH) - 11,
					calendar.get(Calendar.DAY_OF_MONTH));
		} else {
			calendar2.set(calendar.get(Calendar.YEAR),
					calendar.get(Calendar.MONTH) + 1,
					calendar.get(Calendar.DAY_OF_MONTH));
		}
		return calendar2.getTime();
	}

	public static String numMonthToStr(int month) {
		switch (month) {
		case 1:
			return "January";
		case 2:
			return "February";
		case 3:
			return "March";
		case 4:
			return "April";
		case 5:
			return "May";
		case 6:
			return "June";
		case 7:
			return "July";
		case 8:
			return "August";
		case 9:
			return "September";
		case 10:
			return "October";
		case 11:
			return "November";
		case 12:
			return "December";
		default:
			return "None";
		}
	}

	public static Integer getQuarter(int month) {
		switch (month) {
		case 1:
			return 1;
		case 2:
			return 1;
		case 3:
			return 1;
		case 4:
			return 2;
		case 5:
			return 2;
		case 6:
			return 2;
		case 7:
			return 3;
		case 8:
			return 3;
		case 9:
			return 3;
		case 10:
			return 4;
		case 11:
			return 4;
		case 12:
			return 4;
		default:
			return 0;
		}
	}

	public static Date endMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1 * calendar.get(Calendar.DATE) + 1);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	public static Date getDateParamater(Date crrDate, int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(crrDate);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.clear();
		calendar2.set(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE)
						+ date);
		return calendar2.getTime();
	}

	public static Date getAfterMonth(Date crrDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(crrDate);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.clear();
		calendar2.set(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH) - 1, calendar.get(Calendar.DATE));
		return calendar2.getTime();
	}

	public static String localizedFormat(Date date, Locale locale) {
		DateFormat dateInstance = DateFormat.getDateInstance(DateFormat.LONG,
				locale);
		String result = dateInstance.format(date);
		return result;
	}

	public static Date addDay(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}
	
	public static Date addYear(Date date, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, year);
		return calendar.getTime();
	}
	
	public static Date getMondayWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Date result = date;   
		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			calendar.add(Calendar.DATE, -1);
			result = calendar.getTime();
	    };
		return result;
	}
	
}
