package com.herokuapp.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.herokuapp.utilities.TestBase;
import com.hrrokuapp.endpoints.UserEndPoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class UserTest_for_Add_PartialUpdate_DeleteBooking extends TestBase{

	//perform logger using log4j
	private static final Logger logger = LogManager.getLogger(UserTest_for_Add_PartialUpdate_DeleteBooking.class);
	//here we create token
	@Test(priority=0)
	public void createToken() {
		getLogger().info("***************creating Token******************");
		logger.info("\"***************creating Token******************\"");
		Response response = UserEndPoints.createToken(tokenPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		getLogger().info("***************Token created******************");
		logger.info("\"***************Token created******************\"");
		
		
		// Deserialize JSON response using ResponseCreatedTokenPayload class
		getLogger().info("***************performing deserialization on created token******************");
		logger.info("\"***************deserialize created token******************\"");
		TestBase.deserializeCreatedToken(response);
		getLogger().info("***************deserialization performed on created token******************");
		logger.info("\"***************deserialization performed on created token*****************\"");

	}
	//here we create booking
	@Test(priority=1)
	public void testcreateBooking() {
		getLogger().info("***************creating booking******************");
		logger.info("********************creating booking***********************");
		Response response = UserEndPoints.createBooking(bookingPayload);
		response.then().log().all();
		String res =(response).asString();
 		JsonPath x = new JsonPath(res);
		id = x.getString("bookingid").toString();
		Assert.assertEquals(response.getStatusCode(), 200);
		getLogger().info("***************booking is created******************");
		logger.info("********************booking is created***********************");
		
		
	// Deserialization JSON response using ResponseBookingPayload class
		getLogger().info("***************perform deserialization on created booking******************");
		logger.info("********perform deserialization on created booking**********");
		TestBase.deserializationof_createdBooking(response);
		getLogger().info("***************deserialization performed on created booking above is the details******************");
		logger.info("**deserialization performed on created booking above is the details*******");
	}
	
	//here we partial update created booking
	@Test(priority=2)
	public void partiallupdatecreatedBooking() {
		getLogger().info("***************perfrom partial update on created booking******************");
		logger.info("*******perfrom partial update on created booking*******");
		bookingPayload.setFirstname(faker.name().firstName());
		Response response = UserEndPoints.partialupdateBooking(id,bookingPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*******partial update has performed on created booking*******");
		getLogger().info("***************partial update has performed on created booking******************");
		
		//perform deserialization using ResponseUpdatecreateBookingPayload pojo class
		getLogger().info("***************perform deserialization on partial update created booking******************");
		logger.info("****perform deserialization on partial update created booking******************");
		TestBase.deserializationofUpdate_get_partialUpdate_createdBookibg(response);
		logger.info("****deserialization performed on partial update created booking******************");
		getLogger().info("***************deserialization performed on partial update created booking******************");
	}

	//here we delete the created booking
	@Test(priority=3)
	public void deleteBooking() {
		getLogger().info("***************performing deletion on created booking******************");
		logger.info("****performing deletion on created booking******************");
		Response response = UserEndPoints.deleteBooking(id);
		response.then().log().all();
		String responseBody = response.getBody().asString();
		System.out.println("*******"+responseBody);
		Assert.assertEquals(response.getStatusCode(), 201);
		getLogger().info("***************deletion is performed on created booking******************");
		logger.info("****deletion is performed on created booking******************");

	}


}
