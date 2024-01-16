package tests;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseTest;
import utils.DataBuilder;

import static org.testng.Assert.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class BaseComponentTest extends BaseTest {

	String id;
	
	@Test(priority=1)
	public void postATodo() {
		Response resp = doPostRequest("api/save", DataBuilder.buildTodo().toJSONString(), 200);
		id = resp.jsonPath().getString("id");
		//assertEquals(resp.jsonPath().getString("info"), "Todo saved! Nice job!");//TestNG assert
		assertThat(resp.jsonPath().getString("info"), is(equalTo("Todo saved! Nice job!")));//Hamcrest assert	
	}
	
	@Test(priority=2)
	public void getATodo() {
		Response resp = doGetRequest("api/"+id, 200);
		System.out.println(resp.asPrettyString());
		assertThat(resp.jsonPath().getString("_id"),is(id));
	}
	
	@Test(priority = 3)
	public void updateATodo() {
		Response resp = doPutRequest("api/todos/"+id, DataBuilder.buildTodo().toJSONString(), 201);
		assertThat(resp.jsonPath().getString("msg"), is(equalTo("Item updated")));
	}
	
	@Test(priority=4)
	public void deleteTodo() {
		Response resp = doDeleteRequest("api/delete/"+id, 200);
		assertThat(resp.jsonPath().getString("msg"), is(equalTo("Event deleted.")));

	}
	
	
/*	//@Test(priority=1)
	public void postAUser() {
		Response resp = doPostRequest("api/users", null, 201);
	} */
	
}
