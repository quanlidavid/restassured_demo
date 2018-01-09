import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.Payload;
import files.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Basics2 {
	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException {
		FileInputStream fis = new FileInputStream(Class.class.getClass().getResource("/").getPath()+"files/env.properties");
		prop.load(fis);
	}
	
	@Test
	public void postData() {
		RestAssured.baseURI = prop.getProperty("HOST");
		
		given()
			.queryParam("key", prop.getProperty("KEY"))
			.body(Payload.getAddPlacePostData())
			.when().post(Resources.placePostData())
			.then().assertThat().statusCode(200).and()
			.contentType(ContentType.JSON).and().body("status", equalTo("OK"));
		
		// Create a place (place id in response)
		// and delete that place (Request - place id)
		
	}
}
