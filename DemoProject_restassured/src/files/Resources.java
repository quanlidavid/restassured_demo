package files;

import java.net.URI;

public class Resources {
	public static String placeAddJson() {
		String res = "/maps/api/place/add/json";
		return res;
	}

	public static String placeAddXml() {
		String res = "/maps/api/place/add/xml";
		return res;
	}

	public static String placeDeleteJson() {
		String res = "/maps/api/place/delete/json";
		return res;
	}

	public static String nearbysearchJson() {
		String res = "/maps/api/place/nearbysearch/json";
		return res;
	}

	public static String jiraLogin() {
		String res = "/rest/auth/1/session";
		return res;
	}

	public static String jiraCreateIssue() {
		String res = "/rest/api/2/issue";
		return res;
	}

	public static String jiraAddCommentToIssue(String issueIdOrKey) {
		String res = "/rest/api/2/issue/" + issueIdOrKey + "/comment";
		return res;
	}

	public static String jiraDeleteCommentOfIssue(String issueIdOrKey, String commentId) {
		String res = "/rest/api/2/issue/" + issueIdOrKey + "/comment/" + commentId;
		return res;
	}

	public static String getTweet() {
		return "/home_timeline.json";
	}

	public static String createTweet() {
		return "/update.json";
	}

	public static String deleteTweet(String tweetId) {
		return String.format("/destroy/%s.json", tweetId);
	}
}
