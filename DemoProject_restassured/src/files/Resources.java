package files;

import java.net.URI;

public class Resources {
	public static String placePostData() {
		String res = "/maps/api/place/add/json";
		return res;
	}
	
	public static String placePostDataXml() {
		String res = "/maps/api/place/add/xml";
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
	public static String jiraLoginPostData() {
		String res = "/rest/auth/1/session";
		return res;
	}
	public static String jiraCreateIssuePostData() {
		String res = "/rest/api/2/issue";
		return res;
	}
	public static String jiraAddCommentPostData(String issueIdOrKey) {
		String res = "/rest/api/2/issue/" + issueIdOrKey + "/comment";
		return res;
	}

	public static String jiraDeleteCommentDeleteData(String issueIdOrKey, String commentId) {
		String res = "/rest/api/2/issue/" + issueIdOrKey + "/comment/"+commentId;
		return res;
	}
}
