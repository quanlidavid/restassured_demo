import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

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
		FileInputStream fis = new FileInputStream("./files/evn.properties");
		prop.load(fis);
	}
	
	@Test
	public void Test() {
		// BaseURL or Host
		RestAssured.baseURI = prop.getProperty("HOST");

		given().
				param("location", "-33.8670522,151.1957362").param("radius", "500").param("key", prop.getProperty("KEY"))
				.when()
				.get(Resources.nearbysearchGetData())
				.then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and()
				.body("results[0].name", equalTo("Sydney")).and()
				.body("results[0].place_id", equalTo("dasfaf")).and()
				.header("Server", "pablo");
		
		//Status code of the response
		//Content type
		//Body
		//Header response
	}

}
