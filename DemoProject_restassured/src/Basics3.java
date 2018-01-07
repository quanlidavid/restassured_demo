import org.testng.annotations.BeforeTest;
import files.Resources;
import files.Payload;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Basics3 {
	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException {	
		FileInputStream fis = new FileInputStream("/Users/quanli/eclipse-workspace/DemoProject_restassured/src/files/env.properties");
		prop.load(fis);
	}
	
	@Test
	public void AddandDeletePlace() {
		// Create a place (place id in response)
		// and delete that place (Request - place id)
		
		//Task 1- Grab the response
		RestAssured.baseURI = prop.getProperty("HOST");
		Response res = given()
							.queryParam("key", prop.getProperty("KEY"))
							.body(Payload.getAddPlacePostData())
							.when()
							.post(Resources.placePostData())
							.then().assertThat().statusCode(200).and()
							.contentType(ContentType.JSON).and().body("status", equalTo("OK"))
							.extract().response();
		
		// Task 2- Grab the Place ID from response
		String responseString = res.asString();
		System.out.println(responseString);
		
		JsonPath js = new JsonPath(responseString);
		String place_id = js.get("place_id");
		System.out.println(place_id);
		
		//Task 3 place this place id in the Delete request
		String deleteBodyStr = Payload.getDeletePlacePostData(place_id);
		given()
			.queryParam("key", "dasfdasfdasf")
			.body(deleteBodyStr)
			.when()
			.post(Resources.deletePostData())
			.then().assertThat().statusCode(200).and()
			.contentType(ContentType.JSON).and()
			.body("status", equalTo("OK"));
	}
}