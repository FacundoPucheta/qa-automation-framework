package com.facundo.automation.utils;

import java.util.UUID;

/**
 * Utility class for generating test data.
 */
public class TestDataUtils {

    /**
     * Generates a random positive identifier.
     *
     * @return a positive long value to be used as entity ID
     */
    public static long generateId() {
        return UUID.randomUUID().hashCode() & Integer.MAX_VALUE;
    }

    /**
     * Generates the current timestamp in ISO-8601 format.
     *
     * @return current date-time as {@link String} e.g. "2026-02-18T19:16:57.020Z"
     */
    public static String generateShipDate() {
        return java.time.Instant.now().toString();
    }
}
