package files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Payload {
	public static String GenerateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}

	public static String getAddPlacePostData() {
		String bodyStr = "{" + "\"location\": {" + "\"lat\": -33.8669710," + "\"lng\": 151.1958750" + "},"
				+ "\"accuracy\": 50," + "\"name\": \"Google Shoes!\"," + "\"phone_number\": \"(02) 9374 4000\","
				+ "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\"," + "\"types\": [\"shoe_store\"],"
				+ "\"website\": \"http://www.google.com.au/\"," + "\"language\": \"en-AU\"" + "}";
		return bodyStr;
	}

	public static String getAddPlacePostDataXml() throws IOException {
		String bodyStr = GenerateStringFromResource(
				Class.class.getClass().getResource("/").getPath() + "/files/payloads/xml/ÏaddPlacePostData.xml");
		return bodyStr;
	}

	public static String getDeletePlacePostData(String place_id) {
		String bodyStr = "{\"place_id\":\"" + place_id + "\"}";
		return bodyStr;
	}
}
