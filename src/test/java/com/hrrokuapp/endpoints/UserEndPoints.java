package com.hrrokuapp.endpoints;

import com.herokuapp.payloads.BookingPayLoad;
import com.herokuapp.payloads.CreateTokenPayload;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class UserEndPoints {

	public static Response createToken(CreateTokenPayload payload) {
		RestAssured.baseURI = Routes.base_url;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.header("Content-Type","application/json")
				.body(payload)
				.when()
				.post(Routes.createToken_url);
		return response;
	}

	public static Response createBooking(BookingPayLoad payload) {

		RestAssured.baseURI = Routes.base_url;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.header("Content-Type","application/json")
					.header("Accept","application/json")
					.body(payload)
					.when()
					.post("/booking");
//
		return response;
	}

	public static Response getBooking(String id) {
		RestAssured.baseURI = Routes.base_url;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.header("Accept","application/json")
				.when()
				.get("/booking/"+id);
		return response;
	}

	public static Response updateBooking(String id,BookingPayLoad payload) {
		RestAssured.baseURI = Routes.base_url;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.header("Content-Type","application/json")
					.header("Accept","application/json")
					.header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
				.body(payload)
				.when()
				.put("/Booking/"+id);
		return response;
	}
	
	public static Response partialupdateBooking(String id,BookingPayLoad payload) {
		RestAssured.baseURI = Routes.base_url;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.header("Content-Type","application/json")
					.header("Accept","application/json")
					.header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
				.body(payload)
				.when()
				.patch("/Booking/"+id);
		return response;
	}

	public static Response deleteBooking(String id) {
		RestAssured.baseURI = Routes.base_url;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.header("Accept","application/json")
				.header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
				.when()
				.delete("/booking/"+id);
		return response;
	}

}
