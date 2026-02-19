package com.facundo.automation.api.tests;

import com.facundo.automation.api.base.BaseApiTest;
import com.facundo.automation.api.models.Order;
import com.facundo.automation.api.models.Pet;
import com.facundo.automation.utils.TestDataUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * API tests to validate order creation flow for selected pets.
 */
public class PetOrderFlowTest extends BaseApiTest {
    List<Pet> selectedPets;

    @Test
    public void shouldListAvailablePets() {
        Response availablePetsResponse = client.getPetsByStatus("available");
        Assert.assertEquals(availablePetsResponse.getStatusCode(), 200,
                "Error | Failed to list available pets");

        List<Pet> availablePets = new ArrayList<>(availablePetsResponse.jsonPath().getList("", Pet.class));
        Assert.assertTrue(availablePets.size() >= 5,
                "Error | Need at least 5 available pets to proceed");

        Collections.shuffle(availablePets);
        selectedPets = availablePets.subList(0, 5);

        Assert.assertEquals(selectedPets.size(), 5,
                "Error | Exactly 5 pets should be stored");
    }

    @Test(dependsOnMethods = "shouldListAvailablePets")
    public void shouldCreateOrdersForAvailablePets() {
        for (Pet pet : selectedPets) {
            createAndValidateOrder(pet.getId());
        }
    }

    private void createAndValidateOrder(long petId) {
        Order newOrder = new Order(
                TestDataUtils.generateId(),
                petId,
                TestDataUtils.generateShipDate(),
                "placed",
                true
        );

        Response orderResponse = client.createOrder(newOrder);
        String shipDate = orderResponse.jsonPath().getString("shipDate");

        Assert.assertEquals(orderResponse.getStatusCode(), 200,
                "Error | Failed to create order for petId: " + petId);

        Assert.assertTrue(orderResponse.jsonPath().getLong("id") > 0,
                "Error | Order id should be generated and greater than 0");

        Assert.assertEquals(orderResponse.jsonPath().getLong("petId"), petId,
                "Error | Order petId mismatch");

        Assert.assertNotNull(shipDate,
                "Error | shipDate should not be null");

        Assert.assertEquals(orderResponse.jsonPath().getString("status"), "placed",
                "Error | Order status should be placed");

        Assert.assertTrue(orderResponse.jsonPath().getBoolean("complete"),
                "Error | Order should be complete");
    }
}
