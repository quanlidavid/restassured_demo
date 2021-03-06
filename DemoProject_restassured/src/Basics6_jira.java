package tests;
import org.testng.annotations.BeforeTest;
import files.Resources;
import files.ReusableMethods;
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

public class Basics6_jira {
	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException {	
		FileInputStream fis = new FileInputStream(Class.class.getClass().getResource("/").getPath()+"/files/jira.properties");
		prop.load(fis);
	}
	
	@Test
	public void jiraE2E() {
		//Task 1- Login get response
		RestAssured.baseURI = prop.getProperty("JIRAHOST");
		Response response = given()
								.header("Content-Type","application/json")
								.body(Payload.loginJiraPostData())
								.when()
								.post(Resources.jiraLogin())
								.then()
								.assertThat().statusCode(200).and()
								.contentType(ContentType.JSON).and()
								.extract().response();
		
		// Task 2- Grab the session name and value from response
		JsonPath js = ReusableMethods.rawToJson(response);
		String session_name = js.get("session.name");
		String session_value = js.get("session.value");
		
		//Task 3 create a issue and get the issue id
		response = given()
						.header("cookie",session_name + "=" + session_value)
						.header("Content-Type","application/json")
						.body(Payload.createIssueJiraPostData()).log().all()
						.when()
						.post(Resources.jiraCreateIssue())
						.then().assertThat().statusCode(201).and()
						.contentType(ContentType.JSON).log().all()
						.extract().response();
		js = ReusableMethods.rawToJson(response);
		String issueId = js.get("id");
		System.out.println(issueId);
		
		//Task 4 create a comment of the issue
		response = given()
				.header("cookie",session_name + "=" + session_value)
				.header("Accept","application/json")
				.header("Content-Type","application/json")
				.pathParam("issueId", issueId)
				.body(Payload.addCommentJiraPostData()).log().all()
				.when()
				.post(Resources.jiraAddCommentToIssue("{issueId}"))
				.then().assertThat().statusCode(201).and()
				.contentType(ContentType.JSON).log().all()
				.extract().response();
		js = ReusableMethods.rawToJson(response);
		String commentId = js.get("id");
		System.out.println(commentId);
		
		//Task 5 delete a comment of the issue
		response = given()
				.header("cookie",session_name + "=" + session_value)
				.header("Accept","application/json")
				.pathParam("issueId", issueId)
				.pathParam("commentId", commentId)
				.log().all()
				.when()
				.delete(Resources.jiraDeleteCommentOfIssue("{issueId}","{commentId}"))
				.then().assertThat().statusCode(204).and()
				.contentType(ContentType.JSON).log().all()
				.extract().response();
	}
}
