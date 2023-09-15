package com.hrrokuapp.endpoints;

/*
  https://restful-booker.herokuapp.com/auth
https://restful-booker.herokuapp.com/booking
https://restful-booker.herokuapp.com/booking?firstname=Susan&lastname=Wilson
https://restful-booker.herokuapp.com/booking?checkin=2020-08-23&checkout=2022-03-26
https://restful-booker.herokuapp.com/booking/779
https://restful-booker.herokuapp.com/booking
https://restful-booker.herokuapp.com/booking/779
https://restful-booker.herokuapp.com/booking/2079
https://restful-booker.herokuapp.com/booking/2079
 */
public class Routes {
	public static String base_url = "https://restful-booker.herokuapp.com";

	//Booking model
	public static String createToken_url =base_url+"/auth";
	public static String post_url =base_url+"/booking";
	public static String get_url =base_url+"/booking/";
	public static String partiallyUpdate_url =base_url+"/booking/id";
	public static String Update_url =base_url+"/booking/id";
	public static String delete_url =base_url+"/booking/id";




}
