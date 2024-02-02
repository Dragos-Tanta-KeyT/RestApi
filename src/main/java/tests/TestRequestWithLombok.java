package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TestRequestWithLombok {
	
	@Test
	public void createBooking() {
		BookingDates bookDates = new BookingDates("2024-02-02", "2024-02-12");
		Booking booking = new Booking("Ion", "Popescu", 250, false, bookDates, "breakfast");
		
		Response resp =  given().
				contentType(ContentType.JSON).
				header("accept", "application/json").
				body(booking).
				post("https://restful-booker.herokuapp.com/booking").
				then().statusCode(200).
				extract().response();
				
		System.out.println(resp.asPrettyString());
		
		BookingId bookingId = resp.as(BookingId.class);
		System.out.println("---------------------------------------");
		System.out.println(bookingId.toString());
		
		assertEquals(booking.toString(), bookingId.getBooking().toString());
		System.out.println(bookingId.getBookingid());
		
	}
	
	
	

}
