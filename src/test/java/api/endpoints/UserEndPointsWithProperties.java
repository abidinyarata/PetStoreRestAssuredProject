package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPointsWithProperties {
	
	// Created for getting URL's from properties file
	static ResourceBundle getURL()
	{
		return ResourceBundle.getBundle("routes");	// load properties file
	}

	public static Response createUser(User userPayload)
	{
		String postUrl = getURL().getString("postUrl");
		
		return given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(userPayload)
		
		.when()
			.post(postUrl);
	}
	
	public static Response readUser(String userName)
	{
		String getUrl = getURL().getString("getUrl");
		
		return given()
			.pathParam("username", userName)
		
		.when()
			.get(getUrl);
	}
	
	public static Response updateUser(User userPayload, String userName)
	{
		String updateUrl = getURL().getString("updateUrl");
		
		return given()
			.pathParam("username", userName)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(userPayload)
		
		.when()
			.put(updateUrl);
	}
	
	public static Response deleteUser(String userName)
	{
		String deleteUrl = getURL().getString("deleteUrl");
		
		return given()
			.pathParam("username", userName)
		
		.when()
			.delete(deleteUrl);
	}
}