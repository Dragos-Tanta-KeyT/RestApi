package tests;


import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;


public class SchemaValidationExample {

	
	@Test
	public void validationSchemaTest() {
		
		Response resp =  given().
				get("https://keytrcrud.herokuapp.com/api/users/65aac6f2fb0f6a00183aaca2").
				then().extract().response();
		
		System.out.println(resp.asPrettyString());
		
		assertThat(resp.asString(), matchesJsonSchemaInClasspath("schema.json"));
		
	}
	
}
