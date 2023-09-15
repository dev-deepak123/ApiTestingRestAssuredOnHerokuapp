package com.herokuapp.utilities;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.testng.annotations.BeforeClass;
import com.github.javafaker.Faker;
import com.herokuapp.payloads.BookingPayLoad;
import com.herokuapp.payloads.CreateTokenPayload;
import com.herokuapp.payloads.ResonseCreatedTokenPayload;
import com.herokuapp.payloads.ResponseUpdatecratedBookingPayload;
import com.herokuapp.payloads.ResponsecreateBookingPayload;

import io.restassured.response.Response;


public class TestBase {
    private static final Logger log = Logger.getLogger(TestBase.class.getName());

    //This is implementaion of console level log
    static {
        log.setLevel(Level.INFO);
    }

    public static Logger getLogger() {
        return log;
    }
    public CreateTokenPayload tokenPayload;
	public static BookingPayLoad bookingPayload;
	public String id="";
	public static Faker faker;
	 
	//This below method run each and every class in the framework
    @BeforeClass
    public void setUp() {

    	//Here we setup the Bookingpay load class and send dynamic data using the faker class
			faker = new Faker();
		    bookingPayload = new BookingPayLoad();
	        bookingPayload.setFirstname(faker.name().firstName());
	        bookingPayload.setLastname(faker.name().lastName());
	        bookingPayload.setTotalprice(faker.number().numberBetween(100, 500));
	        bookingPayload.setDepositpaid(true);
	        BookingPayLoad.BookingDates bookingDates = new BookingPayLoad.BookingDates();
	        bookingDates.setCheckin("2023-01-01");
	        bookingDates.setCheckout("2023-12-31");
	        bookingPayload.setBookingdates(bookingDates);
	        bookingPayload.setAdditionalneeds(faker.food().dish());

	        //here we setup createToken payload class and send data as admin and password123
	        tokenPayload = new CreateTokenPayload();
	        tokenPayload.setUsername("admin");
	        tokenPayload.setPassword("password123");


	}
    
    //here we performed deserialization using ResonseCreatedTokenPayload.class
    public static void deserializeCreatedToken(Response response){
    	ResonseCreatedTokenPayload token = response.getBody().as(ResonseCreatedTokenPayload.class);
        // Access and print details from the deserialized response
        System.out.println("token: " + token.getToken());
    }
    
    //here we performed deserialization using ResponsecreateBookingPayload.class
    public static void deserializationof_createdBooking(Response response){
    	// Deserialize JSON response using Booking payload class class
    	 ResponsecreateBookingPayload bookingResponse = response.getBody().as(ResponsecreateBookingPayload.class);
         System.out.println("Booking ID: " + bookingResponse.getBookingid());
         System.out.println("First Name: " + bookingResponse.getBooking().getFirstname());
         System.out.println("Last Name: " + bookingResponse.getBooking().getLastname());
         System.out.println("Total Price: " + bookingResponse.getBooking().getTotalprice());
         System.out.println("Checkin Date: " + bookingResponse.getBooking().getBookingdates().getCheckin());
         System.out.println("Checkout Date: " + bookingResponse.getBooking().getBookingdates().getCheckout());
         System.out.println("Deposite Paid: " + bookingResponse.getBooking().isDepositpaid());
         System.out.println("Aditional Needs: " + bookingResponse.getBooking().getAdditionalneeds());
    }
    
    //here we performed deserialization using ResponseUpdatecratedBookingPayload.class
    public static void deserializationofUpdate_get_partialUpdate_createdBookibg(Response response){
    	// Deserialize JSON response using ResponseBookingPayload class
    			ResponseUpdatecratedBookingPayload bookingResponse = response.getBody().as(ResponseUpdatecratedBookingPayload.class);
    	        // Access and print details from the deserialized response
    	        System.out.println("First Name: " + bookingResponse.getFirstname());
    	        System.out.println("Last Name: " + bookingResponse.getLastname());
    	        System.out.println("Total Price: " + bookingResponse.getTotalprice());
    	        System.out.println("Deposite: " + bookingResponse.isDepositpaid());
    	        System.out.println("Checkin Date: " + bookingResponse.getBookingdates().getCheckin());
    	        System.out.println("Checkout Date: " + bookingResponse.getBookingdates().getCheckout());
    	        System.out.println("Additional Needs: " + bookingResponse.getAdditionalneeds());
    
   }
    
}
