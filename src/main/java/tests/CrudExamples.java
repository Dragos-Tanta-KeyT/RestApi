package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CrudExamples {

	String id;
	JSONObject body;
	
	@BeforeClass
	public void setup() {
		
		RestAssured.baseURI= "https://keytodorestapi.herokuapp.com/";
		
		body =  new JSONObject();
		
		Faker fake =  new Faker();
		
		body.put("title", fake.cat().name());
		body.put("body", fake.chuckNorris().fact());	
		
	}
	
	@Test(priority=1)
	public void postATodoMessage() {
		
		Response response = given().
			//contentType(ContentType.JSON).
			header("Content-type", "application/json").
			body(body.toJSONString()).
			post("api/save").
		then().
			statusCode(200).
			body("info",equalTo("Todo saved! Nice job!"))
			.extract().response();
		id =  response.jsonPath().getString("id");	
	}
	
	@Test(priority=2)
	public void readTodo() {
		
		Response response = given().get("api/"+id).then().extract().response();
		
		System.out.println(response.asPrettyString());
	}
	
}
