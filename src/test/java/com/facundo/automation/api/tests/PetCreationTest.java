package com.facundo.automation.api.tests;

import com.facundo.automation.api.base.BaseApiTest;
import com.facundo.automation.api.models.Pet;
import com.facundo.automation.utils.TestDataUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * API tests for PetStore - validates pet creation by status and pet data retrieval.
 */
public class PetCreationTest extends BaseApiTest {
    private long soldPetId;
    private List<Long> createdPetIds = new ArrayList<>();

    @Test
    public void shouldCreateTenPetsWithCorrectStatuses() {
        for (int i = 1; i <= 5; i++) {
            createAndValidatePet(TestDataUtils.generateId(), "AvailablePet_" + i, "available");
        }

        for (int i = 1; i <= 4; i++) {
            createAndValidatePet(TestDataUtils.generateId(), "PendingPet_" + i, "pending");
        }

        soldPetId = TestDataUtils.generateId();
        createAndValidatePet(soldPetId, "SoldPet", "sold");

        Assert.assertEquals(createdPetIds.size(), 10,
                "Error | Should been created 10 pets");
    }

    @Test(dependsOnMethods = "shouldCreateTenPetsWithCorrectStatuses")
    public void shouldRetrieveSoldPetDetails() {
        Response response = client.getPetById(soldPetId);

        Assert.assertEquals(response.getStatusCode(), 200,
                "Error | Status code was not the expected");

        Assert.assertEquals(response.jsonPath().getLong("id"), soldPetId,
                "Error | Pet ID mismatch");

        Assert.assertEquals(response.jsonPath().getString("status"), "sold",
                "Error | Pet status should be sold");

        Assert.assertNotNull(response.jsonPath().getString("name"),
                "Error | Pet name should not be null");
    }

    private void createAndValidatePet(long id, String name, String status) {
        Pet pet = new Pet(id, name, status);
        Response response = client.createPet(pet);

        Assert.assertEquals(response.getStatusCode(), 200,
                "Error | Failed to create pet with id: " + id);

        Assert.assertEquals(response.jsonPath().getLong("id"), id,
                "Error | Id mismatch");

        Assert.assertEquals(response.jsonPath().getString("name"), name,
                "Error | Name mismatch for pet with id: " + id);

        Assert.assertEquals(response.jsonPath().getString("status"), status,
                "Error | Status mismatch for pet with id: " + id);

        createdPetIds.add(id);
    }
}
