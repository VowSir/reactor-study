
package org.example;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Time Format Processing Class
 */

@Slf4j
public class DateTimeUtil {


	public  static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
	public  static final DateTimeFormatter FORMATTER_USER_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public  static final String UTC = "UTC";


	/**
	 * Time formatting
	 * @param dateTimeStr time field string
	 * @param currentTimeZoneId The time zone of the current string
	 * @param targetTimezoneId Time zone to be converted to
	 * @param formatter Time formatting format
	 * @return
	 */
	public static LocalDateTime makeCurrent2Timezone(String dateTimeStr, String currentTimeZoneId, String targetTimezoneId,DateTimeFormatter formatter) {

		if (dateTimeStr == null || dateTimeStr.isEmpty()) {
			return null;
		}
		try {
			LocalDateTime localDateTime = LocalDateTime.parse(handleStr(dateTimeStr), formatter);
			return ZonedDateTime.of(localDateTime, ZoneId.of(currentTimeZoneId)).withZoneSameInstant(ZoneId.of(targetTimezoneId)).toLocalDateTime();
		} catch (Exception e) {
//			log.error("makeCurrentToTimezone error, msg: {}", e.getMessage());
			return null;
		}
	}


	/**
	 * utc time converted to current time zone
	 * @param dateTimeStr
	 * @param targetTimezoneId
	 * @return
	 */
	public static Optional<LocalDateTime> makeUtc2Current(String dateTimeStr, String targetTimezoneId) {
		return Optional.ofNullable(makeCurrent2Timezone(dateTimeStr, UTC, targetTimezoneId,FORMATTER));
	}


	/**
	 * Current time zone converted to utc time
	 * @param dateTimeStr
	 * @param currentTimeZoneId
	 * @return
	 */
	public static LocalDateTime makeCurrent2Utc(String dateTimeStr,String currentTimeZoneId) {
		return makeCurrent2Timezone(dateTimeStr, currentTimeZoneId, UTC,FORMATTER);
	}

	/**
	 * Current time zone converted to utc time format is the format entered by the user
	 * @param dateTimeStr
	 * @param currentTimeZoneId
	 * @return
	 */
	public static Optional<LocalDateTime> makeCurrent2UtcByInput(String dateTimeStr,String currentTimeZoneId) {
		return Optional.ofNullable(makeCurrent2Timezone(dateTimeStr, currentTimeZoneId, UTC, FORMATTER_USER_INPUT));
	}


	/**
	 * Current time zone converted to utc time format is the format entered by the user
	 * @param dateTimeStr
	 * @param currentTimeZoneId
	 * @return
	 */
	public static Optional<String> makeCurrent2UtcByInputStr(String dateTimeStr,String currentTimeZoneId) {
		return makeCurrent2UtcByInput(dateTimeStr, currentTimeZoneId).map(dateTime -> dateTime.format(FORMATTER_USER_INPUT)).or(() -> Optional.ofNullable(dateTimeStr));
	}

	/**
	 * Determine if it is a time format
	 * @param input
	 * @return
	 */
	public static boolean isTimeString(String input) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
		try {
			formatter.parse(handleStr(input));
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	/**
	 * Correction of date data
	 * @param time
	 * @return
	 */
	public static String handleStr(String time){
		if (time == null || time.isEmpty()) {
			return time;
		}
		time = time.replaceAll("\"", "").split("\\.")[0];
		if (time.length() == 19 && time.contains("T")) {
			time = time + "Z";
		}
		if (time.length() == 16 && time.contains("T")) {
			time = time + ":00Z";
		}
		return time;
	}

	public static void main(String[] args) {
		System.out.println(1);
	}

}
