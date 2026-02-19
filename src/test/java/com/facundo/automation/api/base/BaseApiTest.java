package com.facundo.automation.api.base;

import com.facundo.automation.api.client.PetStoreClient;
import org.testng.annotations.BeforeClass;

/**
 * Base class for all API test classes.
 * <p>
 * Handles Client setup.
 */
public class BaseApiTest {
    protected PetStoreClient client;

    @BeforeClass
    public void setup() {
        client = new PetStoreClient();
    }
}
