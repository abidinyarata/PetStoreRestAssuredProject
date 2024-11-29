package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPointsUseRoutesClass {

	public static Response createUser(User userPayload)
	{
		return given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(userPayload)
		
		.when()
			.post(Routes.postUrl);
	}
	
	public static Response readUser(String userName)
	{
		return given()
			.pathParam("username", userName)
		
		.when()
			.get(Routes.getUrl);
	}
	
	public static Response updateUser(User userPayload, String userName)
	{
		return given()
			.pathParam("username", userName)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(userPayload)
		
		.when()
			.put(Routes.updateUrl);
	}
	
	public static Response deleteUser(String userName)
	{
		return given()
			.pathParam("username", userName)
		
		.when()
			.delete(Routes.deleteUrl);
	}
}