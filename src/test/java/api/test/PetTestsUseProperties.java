package api.test;

import java.util.Locale;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.PetEndPointsWithProperties;
import api.payload.Pet;
import api.payload.PetCategory;
import io.restassured.response.Response;

public class PetTestsUseProperties {

	Faker faker;
	Pet petPayload;
	PetCategory category;

	@BeforeClass
	void setupData() 
	{
		Locale localeTr = Locale.of("tr");
		faker = new Faker(localeTr);
		petPayload = new Pet();
		category = new PetCategory();
		
		category.setId(faker.idNumber().hashCode());
		category.setName(faker.animal().name());
		
		petPayload.setId(faker.idNumber().hashCode());
		petPayload.setCategory(category);
		petPayload.setName(faker.name().firstName());
		petPayload.setStatus("available");
	}

	@Test(priority = 1)
	public void testPostPet() 
	{
		Response response = PetEndPointsWithProperties.addPetToStore(petPayload);

		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	
	@Test(priority = 2)
	public void testGetPetById() 
	{
		Response response = PetEndPointsWithProperties.getPet(petPayload.getId());

		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 3)
	public void testUpdatePetById() 
	{
		// Update data using payload
		petPayload.setName(faker.name().firstName());
		petPayload.setStatus("not available");

		Response response = PetEndPointsWithProperties.updatePet(this.petPayload.getId(), petPayload.getName(), petPayload.getStatus(), petPayload);

		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);

		// Checking data after update
		Response responseAfterUpdate = PetEndPointsWithProperties.getPet(petPayload.getId());
		responseAfterUpdate.then().log().body();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	}

	
	@Test(priority = 4)
	public void testDeletePetById() 
	{
		Response response = PetEndPointsWithProperties.deletePet(petPayload.getId());

		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}
	

}