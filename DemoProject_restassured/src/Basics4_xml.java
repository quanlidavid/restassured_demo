package tests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.Payload;
import files.Resources;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Basics4_xml {
	Properties prop = new Properties();

	@BeforeTest
	public void getData() throws IOException {
		FileInputStream fis = new FileInputStream(
				Class.class.getClass().getResource("/").getPath() + "files/env.properties");
		prop.load(fis);
	}

	@Test
	public void postDataXML() throws IOException {
		RestAssured.baseURI = prop.getProperty("HOST");

		Response response = given().queryParam("key", prop.getProperty("KEY")).body(Payload.getAddPlacePostDataXml()).when()
								.post(Resources.placeAddXml()).then().assertThat().statusCode(200).and()
								.contentType(ContentType.XML)
								.extract().response();
		
		XmlPath xml = ReusableMethods.rawToXML(response);
		String place_id = xml.get("PlaceAddResponse.place_id");
		System.out.println(place_id);
	}
}
