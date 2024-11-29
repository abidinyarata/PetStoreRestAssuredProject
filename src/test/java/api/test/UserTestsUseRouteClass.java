package api.test;

import java.util.Locale;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPointsUseRoutesClass;
import api.payload.User;
import io.restassured.response.Response;

public class UserTestsUseRouteClass {
	
	Faker faker;
	User userPayload;
	
	@BeforeClass
	void setupData()
	{
		Locale localeTr = Locale.of("tr");
		faker = new Faker(localeTr);
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test(priority = 1)
	public void testPostUser()
	{
		Response response = UserEndPointsUseRoutesClass.createUser(userPayload);
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 2)
	public void testGetUserByName()
	{
		Response response = UserEndPointsUseRoutesClass.readUser(userPayload.getUsername());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	public void testUpdateUserByName()
	{
		// Update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPointsUseRoutesClass.updateUser(userPayload, this.userPayload.getUsername());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		// Checking data after update
		Response responseAfterUpdate = UserEndPointsUseRoutesClass.readUser(userPayload.getUsername());
		responseAfterUpdate.then().log().body();	
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	}
	
	@Test(priority = 4)
	public void testDeleteUserByName()
	{
		Response response = UserEndPointsUseRoutesClass.deleteUser(userPayload.getUsername());
		
		response.then().log().all();	
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}