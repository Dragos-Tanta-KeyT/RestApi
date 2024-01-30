package tests;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseTest2;
import utils.DataBuilder;

public class BaseTest2Example extends BaseTest2{

	String id;
	
	@Test(priority=1)
	public void createTodo() {
		Response resp = doRequest("post", "", DataBuilder.buildTodo().toJSONString());
		id = resp.jsonPath().getString("id");
	}
	
	@Test(priority = 2)
	public void readTodo() {
		Response resp = doRequest("Get", id, "");
		System.out.println(resp.asPrettyString());
	}
	
	
	
	
}
