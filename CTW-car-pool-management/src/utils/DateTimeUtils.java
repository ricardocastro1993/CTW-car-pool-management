package utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {

	public static Timestamp getTimestamp(String dateTime) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		long timestamp = 0;

		try {
			Date date = dateFormat.parse(dateTime);
			timestamp = date.getTime(); // Get the timestamp in milliseconds
		} catch (ParseException e) {
			System.out.println("Error parsing date: " + e.getMessage());
		}

		return new Timestamp(timestamp);
	}

	public static String getDateFormat(long timestamp) {

		// Create a SimpleDateFormat object with the desired date and time format
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		// Convert the timestamp to a Date object
		Date date = new Date(timestamp);

		// Format the Date object as a readable string
		String formattedDate = dateFormat.format(date);

		return formattedDate;
	}

}
