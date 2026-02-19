package com.facundo.automation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents an Order entity as defined by the PetStore API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    private long id;
    private long petId;
    private String shipDate;
    private String status;
    private boolean complete;

    public Order() {
    }

    public Order(long id, long petId, String shipDate, String status, boolean complete) {
        this.id = id;
        this.petId = petId;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
