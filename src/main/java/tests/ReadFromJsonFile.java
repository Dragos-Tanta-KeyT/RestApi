package tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class ReadFromJsonFile {
	
	@Test
	public void parseJson() throws IOException, ParseException {
		
		//1. trebuie sa facem un obiect de tip parser pentru JSON
		JSONParser parser =  new JSONParser();
		//2. trebuie sa incarc fisierul pe care vreau sa il parsez
		FileReader file =  new FileReader("data2.json");
		//3. parsez fisierul incarcat la pasul anterior
		Object obj =  parser.parse(file);
		//4. punem continutul intr-un json array
		JSONArray employeeList =  (JSONArray) obj;
		System.out.println(employeeList);
		System.out.println(employeeList.get(0));
		System.out.println(employeeList.get(1));
		//vreau sa iau un camp individual din obiect
		JSONObject employeeObject = (JSONObject) employeeList.get(1);
		System.out.println(employeeObject);
		JSONObject emplyeeAtribute = (JSONObject) employeeObject.get("employee");
		System.out.println(emplyeeAtribute);
		System.out.println(emplyeeAtribute.get("lastName"));
		
		System.out.println("-------------------------------------------");
		
		FileReader file2 = new FileReader("data2.json");
		JsonPath jsonPath =  JsonPath.from(file2);
		System.out.println(jsonPath.getString("[1].employee.lastName"));
		
		System.out.println("----------------");
		System.out.println(jsonPath.getString("[1].employee.company"));
		System.out.println(jsonPath.getString("[1].employee.company[1]"));
		System.out.println(jsonPath.getString("[1].employee.company[1].two.last"));

	}
	

}
