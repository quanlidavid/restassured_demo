package files;

public class Payload {
	public static String getAddPlacePostData() {
		String bodyStr = "\"{\"+\n" + 
				"  \"\\\"location\\\": {\"+\n" + 
				"    \"\\\"lat\\\": -33.8669710,\"+\n" + 
				"    \"\\\"lng\\\": 151.1958750\"+\n" + 
				"  \"},\"+\n" + 
				"  \"\\\"accuracy\\\": 50,\"+\n" + 
				"  \"\\\"name\\\": \\\"Google Shoes!\\\",\"+\n" + 
				"  \"\\\"phone_number\\\": \\\"(02) 9374 4000\\\",\"+\n" + 
				"  \"\\\"address\\\": \\\"48 Pirrama Road, Pyrmont, NSW 2009, Australia\\\",\"+\n" + 
				"  \"\\\"types\\\": [\\\"shoe_store\\\"],\"+\n" + 
				"  \"\\\"website\\\": \\\"http://www.google.com.au/\\\",\"+\n" + 
				"  \"\\\"language\\\": \\\"en-AU\\\"\"+\n" + 
				"\"}\"";
		return bodyStr;
	}
	
	public static String getDeletePlacePostData(String place_id) {
		String bodyStr = "{\"place_id\":\"" + place_id + "\"";
		return bodyStr;
	}
}
