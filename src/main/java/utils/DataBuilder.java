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
	
	public static JSONObject buildToken() {
		
		JSONObject tokenBuilder = new JSONObject();
		tokenBuilder.put("username", "admin");
		tokenBuilder.put("password", "password123");
		return tokenBuilder;
	}
	/*
	 * {
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}'
	 * 
	 * 
	 */
	public static JSONObject buildBooking() {
		
		JSONObject booking  = new JSONObject();
		booking.put("firstname", "Jim");
		booking.put("lastname", "Brown");
		booking.put("totalprice", 250);
		booking.put("depositpaid", true);
			JSONObject bookingDatesData = new JSONObject();
			bookingDatesData.put("checkin", "2024-02-01");
			bookingDatesData.put("checkout", "2024-02-15");
		booking.put("bookingdates", bookingDatesData);
		booking.put("additionalneeds", "Breakfast");

		return booking;
	}
	

}
