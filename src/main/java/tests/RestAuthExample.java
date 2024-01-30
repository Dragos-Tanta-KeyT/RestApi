package tests;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.DataBuilder;

import static io.restassured.RestAssured.given;

public class RestAuthExample {
	
	String token;
	int id;
	
	//@Test
	public void createToken() {
		
		Response resp =  given().
				contentType(ContentType.JSON).
				body(DataBuilder.buildToken().toJSONString()).
				post("https://restful-booker.herokuapp.com/auth").
				then().statusCode(200).extract().response();
		System.out.println(resp.asPrettyString());
		token = resp.jsonPath().getString("token");
	
	}
	
	@Test(priority = 1)
	public void createBooking() {
		
		Response resp =  given().
				contentType(ContentType.JSON).
				//accept(ContentType.JSON).
				header("Accept", "application/json").
				body(DataBuilder.buildBooking().toJSONString()).
				post("https://restful-booker.herokuapp.com/booking").
				then().extract().response();
	
		System.out.println(resp.asPrettyString());
		id = resp.jsonPath().getInt("bookingid");
		
	}
	@Test(priority = 2)
	public void deleteBooking() {
		
	/*	Response resp = given().
				contentType(ContentType.JSON).
				header("Cookie", "token="+token).
				delete("https://restful-booker.herokuapp.com/booking/"+id).
				then().extract().response(); */
		
		Response resp = given().
				auth().preemptive().basic("admin", "password123").
				delete("https://restful-booker.herokuapp.com/booking/"+id).
				then().extract().response();
		
		
		System.out.println(resp.asPrettyString());
	}
	
	
	
}
