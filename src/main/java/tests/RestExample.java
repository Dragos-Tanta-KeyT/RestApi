package tests;

import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;

import java.io.File;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestExample {

	
	@SuppressWarnings("unchecked")
	@Test
	public void restExampleTest() {	
		
		JSONObject requestBody =  new JSONObject();
		requestBody.put("title", "JSON title ");
		requestBody.put("body", "JSON body");
		
		File fisier = new File("fisier.json");
		
		Response response = 
				given().
					header("Content-type", "application/json").
					header("accept", "application/json").
					//ex 1--> direct in body ca string
					//body("{\"title\":\"Test Dragos din java\",\"body\":\"Test Dragos din java\"}").
					//ex 2 --> body as JsonOject
					//body(requestBody.toJSONString()).
					//ex 3 --> body ca si fisier json
					body(fisier).
				when().
					post("https://keytodorestapi.herokuapp.com/api/save").
				then().
					assertThat().statusCode(200).
					extract().response();	
		
		System.out.println(response.asPrettyString());
		System.out.println(response.asString());
		
		String info = response.jsonPath().getString("info");
		System.out.println(info);
		assertEquals(info, "Todo saved! Nice job!");
		assertThat(info, is("Todo saved! Nice job!"));
		
	}
	
	
	
	
}
