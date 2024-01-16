package utils;

import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

public class DataBuilder {
	
	
	public static JSONObject buildTodo() {
		JSONObject todoBuilder = new JSONObject();
		Faker fake = new Faker();
		
		todoBuilder.put("title", fake.cat().name());
		todoBuilder.put("body", fake.chuckNorris().fact());	
		
		return todoBuilder;
	}
	

}
