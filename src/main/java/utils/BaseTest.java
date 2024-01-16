package utils;

import org.testng.annotations.BeforeClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class BaseTest {

	
	@BeforeClass
	public void setup() {
	
		RestAssured.baseURI= "https://keytodorestapi.herokuapp.com/";
		
	}
	
	public static Response doPostRequest(String path, String body, int statusCode ) {
		Response response  = given().
				contentType(ContentType.JSON).
				body(body).
				post(path).
				then().statusCode(statusCode).
				extract().response();
		return response;
	}
	
	public static Response doGetRequest(String path, int statusCode) {
		
		Response resp = given().
				contentType(ContentType.JSON).
				get(path).
				then().statusCode(statusCode).
				extract().response();
		
		return resp;	
	}
	
	public static Response doPutRequest(String path, String body, int statusCode) {
		
		Response response  = given().
				contentType(ContentType.JSON).
				body(body).
				put(path).
				then().statusCode(statusCode).
				extract().response();
		return response;
		
	}
	
	public static Response doDeleteRequest(String path, int statusCode) {
		Response resp = given().
				contentType(ContentType.JSON).
				delete(path).
				then().statusCode(statusCode).
				extract().response();
		
		return resp;
	}


}
