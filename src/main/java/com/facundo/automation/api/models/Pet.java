package com.facundo.automation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents a Pet entity based on the PetStore API specification.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {
    private long id;
    private String name;
    private String status;

    public Pet() {
    }

    public Pet(long id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
