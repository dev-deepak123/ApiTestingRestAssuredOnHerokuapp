package com.herokuapp.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.herokuapp.utilities.TestBase;
import com.hrrokuapp.endpoints.UserEndPoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserTest_for_Add_Update_GetBooking extends TestBase{
	private static final Logger logger = LogManager.getLogger(UserTest_for_Add_Update_GetBooking.class);
	//here we create token
	@Test(priority=0)
	public void createToken() {
		getLogger().info("*************** creating token ******************");
		logger.info("\"***************creating token ******************\"");
		Response response = UserEndPoints.createToken(tokenPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		getLogger().info("***************Token created ******************");
		logger.info("\"***************Token created******************\"");
		
		
		// Deserialize JSON response using ResponseCreatedTokenPayload class
		logger.info("\"***************performing deserialization on created token******************\"");
		getLogger().info("***************performing deserialization ******************");
		TestBase.deserializeCreatedToken(response);
		logger.info("\"***************deserialization performed ******************\"");
		getLogger().info("*************** deserialization performed on created token******************");

	}

	//here we create booking
	@Test(priority=1)
	public void testcreateBooking() {
		getLogger().info("*************** creating booking ******************");
		logger.info("\"*************** creating booking ******************\"");
		Response response = UserEndPoints.createBooking(bookingPayload);
		response.then().log().all();
		String res =(response).asString();
 		JsonPath x = new JsonPath(res);
 		//hare we get id which is for all test in that class
		id = x.getString("bookingid").toString();
		Assert.assertEquals(response.getStatusCode(), 200);
		getLogger().info("*************** Booking Created ******************");
		logger.info("\"***************Booking Created ******************\"");
		
		
		// Deserialization JSON response using ResponseBookingPayload class
		getLogger().info("*************** performing deserialization ******************");
		logger.info("\"***************performing deserialization ******************\"");
		TestBase.deserializationof_createdBooking(response);
		getLogger().info("*************** deserialization performed******************");
		logger.info("\"***************deserialization performed******************\"");

	}
	//here we update booking
	@Test(priority=2)
	public void updatecreatedBooking() {
		getLogger().info("*************** update created booking******************");
		logger.info("********************update created booking***********************");
		 bookingPayload.setFirstname(faker.name().firstName());
	     bookingPayload.setLastname(faker.name().lastName());
		Response response = UserEndPoints.updateBooking(id,bookingPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		getLogger().info("***************created booking has updated ******************");
		logger.info("********************created booking has updated***********************");
		
		// Deserialization JSON response using ResponseBookingPayload class
		getLogger().info("*************** perform deserialization on upadte booking******************");
		logger.info("\"***************perform deserialization on upadte booking******************\"");
		TestBase.deserializationofUpdate_get_partialUpdate_createdBookibg(response);
		getLogger().info("***************deserialization performed on update booking*******************");
		logger.info("\"***************deserialization performed on update booking******************\"");
	}

	//hare we get booking 
	@Test(priority=3)
	public void getBooking() {
		getLogger().info("*************** getBooking deatils from updated created booking******************");
		logger.info("\"***************getBooking deatils from updated created booking******************\"");
		
		Response response = UserEndPoints.getBooking(id);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		getLogger().info("***************above we getBooking deatails from updated created booking******************");
		logger.info("\"***************above we getBooking deatails from updated created booking******************\"");
		
		// Deserialization JSON response using ResponseUpdatecreateBooking class
		getLogger().info("***************perform deserialization on getBooking******************");
		logger.info("\"***************perform deserialization on getBooking******************\"");
		TestBase.deserializationofUpdate_get_partialUpdate_createdBookibg(response);
		getLogger().info("*************** deserialization performed on getBooking and above is the details******************");
		logger.info("\"***************deserialization performed on getBooking and above is the details******************\"");

	}


}
