package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.List;

import org.testng.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static utils.NumberChecker.*;

public class HamcrestExample {

	
	@SuppressWarnings("unchecked")
	@Test
	public void hamcrestTest() {
		
		Response resp =  given().
				get("https://swapi.dev/api/planets/1/").
				then().extract().response();
		
		System.out.println(resp.asString());
		JsonPath jsonPath = resp.jsonPath();
		
		String name =  jsonPath.getString("name");
		System.out.println(name);
		
		//TestNg assert
		assertEquals(name, "Tatooine");
		
		//Hamcrest assert
		//assertThat(name, "Tatooine");
		assertThat(name, equalTo( "Tatooine"));
		assertThat(name, is("Tatooine"));//is(T value)
		assertThat(name, is(equalTo("Tatooine")));//is(Matcher<T>)
		
		//TestNG assert
		assertNotEquals(name, "Mars");
		
		//hamcrest assert
		assertThat(name, is(not("Mars")));
		assertThat(name, is(not(equalTo("Mars"))));
		assertThat(name, is(not(instanceOf(Integer.class))));
		assertThat(name, is(instanceOf(String.class)));
		
		//starts-with
		assertThat(name, startsWith("Tat"));
		assertThat(resp.asString(), startsWith("{\"name\""));
		assertThat(resp.asString(), startsWithIgnoringCase("{\"NaMe\""));
		
		//ends-with
		assertThat(resp.asString(), endsWith("api/planets/1/\"}"));
		assertThat(resp.asString(), endsWithIgnoringCase("Api/PlAnEtS/1/\"}"));
		
		//containsString
		assertThat(name, containsString("tooi"));
		assertThat(name, containsStringIgnoringCase("tOOi"));
		
		//pattern
		assertThat(name, matchesPattern("[a-zA-Z]+"));
		name = "Tatooine123";
		assertThat(name, matchesPattern("[a-zA-Z0-9]+"));
		String diameter =  jsonPath.getString("diameter");
		assertThat(diameter, matchesPattern("[0-9]+"));
		
		
		List<String> films = jsonPath.getList("films");
		System.out.println(films.get(1));
		
		assertThat(films, contains(
			       "https://swapi.dev/api/films/1/", 
			        "https://swapi.dev/api/films/3/", 
			        "https://swapi.dev/api/films/4/", 
			        "https://swapi.dev/api/films/5/", 
			        "https://swapi.dev/api/films/6/"));
		
		assertThat(films, contains(
				startsWith("https:"),
				endsWith("3/"),
				equalTo("https://swapi.dev/api/films/4/"),
				startsWith("https:"),
				endsWith("6/")));
		
		
		
	assertThat(films, hasItem("https://swapi.dev/api/films/4/"));
	assertThat(films, hasItems("https://swapi.dev/api/films/1/", 
	        "https://swapi.dev/api/films/6/"));
	assertThat(films, hasItem(startsWith("http")));
	assertThat(films, hasItem(containsString("swapi.dev")));
	assertThat(films, hasItems(startsWith("http"), containsString("swapi")));
	
	assertThat(films, hasSize(5));
	assertThat(films, hasSize(lessThan(10)));
	assertThat(8, is(lessThan(12)));
	assertThat(films, hasSize(greaterThan(3)));
	
	String[] array = { jsonPath.getString("name"), jsonPath.getString("rotation_period"),
			jsonPath.getString("orbital_period"),jsonPath.getString("diameter"),
			jsonPath.getString("climate")};
	
	assertThat(array, arrayContaining("Tatooine", "23", "304", "10465", "arid"));
	assertThat(array, arrayContainingInAnyOrder("304", "10465", "arid", "Tatooine", "23"));
	
	assertThat(array, is(not(emptyArray())));
	assertThat(array, is(not(nullValue())));

	
	assertThat(resp.asString(), stringContainsInOrder("terrain", "url"));
	//and
	assertThat(resp.asString(), both(containsString("population")).and(containsString("terrain")));
	//or
	assertThat(name, either(is("Tatooine123")).or(is("Tatooine2")).or(is("Terra")).or(is("Mars")));
	assertThat(resp.asString(), either(containsString("population")).or(containsString("terrain")));

	/*
	 * "diameter": "10465", 
    	"climate": "arid", 
    	"gravity": "1 standard", 
	 * 
	 */
	String diameter2 =  jsonPath.getString("diameter");
	String climate = jsonPath.getString("climate");
	String gravity = jsonPath.getString("gravity");
	
	System.out.println("--------------------------------");
	System.out.println(diameter2);
	System.out.println(climate);
	System.out.println(gravity);

	
	assertThat(diameter2, is(numbersOnly()));
	assertThat(climate, is(numbersOnly()));

	}
	
	
	
}
