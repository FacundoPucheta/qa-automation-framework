package com.facundo.automation.api.client;

import com.facundo.automation.api.models.Order;
import com.facundo.automation.api.models.Pet;
import com.facundo.automation.ui.utils.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * HTTP client for the PetStore API. Request configuration.
 */
public class PetStoreClient {

    public PetStoreClient() {
        RestAssured.baseURI = ConfigManager.get("api.base.url");
    }

    // PET
    public Response createPet(Pet pet) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post("/pet");
    }

    public Response getPetsByStatus(String status) {
        return RestAssured.given()
                .queryParam("status", status)
                .when()
                .get("/pet/findByStatus");
    }

    public Response getPetById(long id) {
        return RestAssured.given()
                .when()
                .get("/pet/{$id}", id);
    }

    //ORDER
    public Response createOrder(Order order) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .post("/store/order");
    }

}