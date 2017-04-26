package com.datawings.app.common;

import java.util.Calendar;
import java.util.Date;

public class DateUtilz {

	public static float getHours(Date startDate, Date endDate,
			boolean checkHoliday) {
		// float totalHoursOff = 0;
		// int totalDaysOff = 0;
		float totalHours = 0;
		Float rs = new Float(0.0);

		Calendar caStartDate = Calendar.getInstance();
		caStartDate.setTime(startDate);
		Calendar caEndDate = Calendar.getInstance();
		caEndDate.setTime(endDate);

		if (caEndDate.after(caStartDate)) {
			if (caStartDate.get(Calendar.DAY_OF_MONTH) == caEndDate
					.get(Calendar.DAY_OF_MONTH)
					&& caStartDate.get(Calendar.MONTH) == caEndDate
							.get(Calendar.MONTH)
					&& caStartDate.get(Calendar.YEAR) == caEndDate
							.get(Calendar.YEAR)) {

				int tempHourEnd = caEndDate.get(Calendar.HOUR_OF_DAY);
				int tempMinEnd = caEndDate.get(Calendar.MINUTE);

				int tempHourStart = caStartDate.get(Calendar.HOUR_OF_DAY);
				int tempMinStart = caStartDate.get(Calendar.MINUTE);

				totalHours = ((tempHourEnd * 60 + tempMinEnd) - (tempHourStart * 60 + tempMinStart));
				totalHours = Math.round(totalHours / 60);

				if (tempHourStart <= 12 && tempHourEnd >= 13) {
					totalHours -= 1;
				}

				rs = totalHours;

			} else {
				Calendar temp = Calendar.getInstance();
				temp.setTime(startDate);

				totalHours = calculateHour(temp, 0);
				rs += totalHours;
				
				for (temp.add(Calendar.DATE, 1); temp.before(caEndDate); temp
						.add(Calendar.DATE, 1)) {
					if (temp.get(Calendar.DAY_OF_MONTH) < caEndDate
							.get(Calendar.DAY_OF_MONTH)
							|| temp.get(Calendar.MONTH) < caEndDate
									.get(Calendar.MONTH)
							|| temp.get(Calendar.YEAR) < caEndDate
									.get(Calendar.YEAR)) {

						totalHours = calculateHour(temp, 1);
						rs += totalHours;
						
					}else{
						totalHours = calculateHour(caEndDate, 2);
						rs += totalHours;
					}
				}
			}
		}
		return rs;
	}

	public static float calculateHour(Calendar test, int testcase) {
		int tempHour = test.get(Calendar.HOUR_OF_DAY);
		int tempMin = test.get(Calendar.MINUTE);
		float totalHour = 0;
		switch (testcase) {
		case 0:
			if (tempHour < 13) {
				totalHour = ((16 * 60) - (tempHour * 60 + tempMin));
			} else {
				totalHour = ((17 * 60) - (tempHour * 60 + tempMin));
			}
			totalHour = Math.round(totalHour / 60);
			break;
		case 1:
			totalHour = 8;
			break;
		case 2:
			if (tempHour > 13) {
				totalHour = ((tempHour * 60 + tempMin) - (9 * 60));
			} else {
				totalHour = ((tempHour * 60 + tempMin) - (8 * 60));
			}
			totalHour = Math.round(totalHour / 60);
			break;

		default:
			break;
		}	
		return totalHour;
	}
}
