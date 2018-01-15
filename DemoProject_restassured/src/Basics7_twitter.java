package tests;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.Resources;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Basics7_twitter {

	Properties prop = new Properties();
	String consumerKey = "consumerKey";
	String consumerSecret = "consumerSecret";
	String accessToken = "accessToken";
	String secretToken = "secretToken";
	String tweetId;

	@BeforeTest
	public void getData() throws IOException {
		FileInputStream fis = new FileInputStream(
				Class.class.getClass().getResource("/").getPath() + "/files/twitter.properties");
		prop.load(fis);
	}

	@Test
	public void getLatestTweet() {
		RestAssured.baseURI = prop.getProperty("TWITTERHOST");

		Response response = given()
								.auth()
								.oauth(consumerKey, consumerSecret, accessToken, secretToken)
								.queryParam("count", "1")
								.when()
								.get(Resources.getTweet())
								.then()
								.extract().response();
		
		JsonPath jsonPath = ReusableMethods.rawToJson(response);
		String tweetId = jsonPath.get("id");
		String tweetText = jsonPath.get("text");
	}
	
	@Test
	public void createTweet() {
		RestAssured.baseURI = prop.getProperty("TWITTERHOST");

		Response response = given()
								.auth()
								.oauth(consumerKey, consumerSecret, accessToken, secretToken)
								.queryParam("status", "I created this tweet")
								.when()
								.post(Resources.createTweet())
								.then()
								.extract().response();
		
		JsonPath jsonPath = ReusableMethods.rawToJson(response);
		tweetId = jsonPath.get("id").toString();
	}
	
	@Test
	public void deleteTweet() {
		RestAssured.baseURI = prop.getProperty("TWITTERHOST");

		Response response = given()
								.auth()
								.oauth(consumerKey, consumerSecret, accessToken, secretToken)
								.when()
								.post(Resources.deleteTweet(tweetId))
								.then()
								.extract().response();
		
		JsonPath jsonPath = ReusableMethods.rawToJson(response);
		String tweetId = jsonPath.get("id").toString();
	}
	
	@Test
	public void E2E() {
		createTweet();
		RestAssured.baseURI = prop.getProperty("TWITTERHOST");

		Response response = given()
								.auth()
								.oauth(consumerKey, consumerSecret, accessToken, secretToken)
								.when()
								.post(Resources.deleteTweet(tweetId))
								.then()
								.extract().response();
		
		JsonPath jsonPath = ReusableMethods.rawToJson(response);
		String tweetId = jsonPath.get("id").toString();
	}
}
