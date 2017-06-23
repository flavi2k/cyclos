package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class ToBeDeleted {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYY HH:mm");
		Calendar calendar = Calendar.getInstance();
		TimeZone gmtTime = TimeZone.getTimeZone("GMT");
		sdf.setTimeZone(gmtTime);
		String strDate = sdf.format((calendar.getTime()));
		System.out.println(strDate);

	}

}
