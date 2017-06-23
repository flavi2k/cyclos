package test;

import java.util.HashMap;
import java.util.Map;

public class ToBeDeleted {

	public static void main(String[] args) {
		/*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYY HH:mm");
		Calendar calendar = Calendar.getInstance();
		TimeZone gmtTime = TimeZone.getTimeZone("GMT");
		sdf.setTimeZone(gmtTime);
		String strDate = sdf.format((calendar.getTime()));
		System.out.println(strDate);*/
		
		
		Map<String, String> objMap = new HashMap<String, String>();
	    objMap.put("Name", "Suzuki");
	    objMap.put("Power", "220");
	    objMap.put("Type", "2-wheeler");
	    objMap.put("Price", "85000");
	    System.out.println("Elements of the Map:");
	    System.out.println(objMap);
	    System.out.println(objMap.keySet());

	}

}
