package files;

import java.net.URI;

public class Resources {
	public static String placePostData() {
		String res = "/maps/api/place/add/json";
		return res;
	}

	public static String deletePostData() {
		String res = "/maps/api/place/delete/json";
		return res;
	}
	public static String nearbysearchGetData() {
		String res = "/maps/api/place/nearbysearch/json";
		return res;
	}
	
}
