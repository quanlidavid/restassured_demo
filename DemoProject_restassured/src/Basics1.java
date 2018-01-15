package tests;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Basics1 {
	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException {
		System.out.println(Class.class.getClass().getResource("/").getPath());
		System.out.println(Basics1.class.getClassLoader().getResource("").getPath());

		FileInputStream fis = new FileInputStream(Class.class.getClass().getResource("/").getPath()+"files/env.properties");
		prop.load(fis);
	}
	
	@Test
	public void getPlaceAPI() {
		// BaseURL or Host
		RestAssured.baseURI = prop.getProperty("HOST");

		given().
				param("location", "-33.8670522,151.1957362").param("radius", "500").param("key", prop.getProperty("KEY"))
				.when()
				.get(Resources.nearbysearchJson())
				.then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and()
				.body("results[0].name", equalTo("Sydney")).and()
				.body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and()
				.header("Server", "scaffolding on HTTPServer2");
		
		//Status code of the response
		//Content type
		//Body
		//Header response
	}

}
