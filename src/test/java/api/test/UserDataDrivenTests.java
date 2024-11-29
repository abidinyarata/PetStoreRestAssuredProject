package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPointsUseRoutesClass;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserDataDrivenTests {

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostuser(String userId, String userName, String firstName, String lastName, String mail,
			String password, String phone) 
	{
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(userName);
		userPayload.setFirstName(firstName);
		userPayload.setLastName(lastName);
		userPayload.setEmail(mail);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);
		
		Response response = UserEndPointsUseRoutesClass.createUser(userPayload);
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String username)
	{
		Response response = UserEndPointsUseRoutesClass.deleteUser(username);
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}