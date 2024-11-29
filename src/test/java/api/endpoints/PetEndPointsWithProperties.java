package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndPointsWithProperties {
	
	static ResourceBundle getURL()
	{
		return ResourceBundle.getBundle("routes");	
	}

	public static Response addPetToStore(Pet petPayload)
	{
		String postUrl = getURL().getString("postPetUrl");
		
		return given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(petPayload)
		
		.when()
			.post(postUrl);
	}
	
	
	public static Response getPet(int id)
	{
		String getUrl = getURL().getString("getPetUrl");
		
		return given()
			.pathParam("id", id)
		
		.when()
			.get(getUrl);
	}
	
	public static Response updatePet(int id, String name, String status, Pet petPayload)
	{
		String updateUrl = getURL().getString("updatePetUrl");
		
		return given()
			.formParam("name", name)
			.formParam("status", status)
			.pathParam("id", id)
		
		.when()
			.post(updateUrl);
	}
	
	
	public static Response deletePet(int id)
	{
		String deleteUrl = getURL().getString("deletePetUrl");
		
		return given()
			.pathParam("id", id)
		
		.when()
			.delete(deleteUrl);
	}
	
}