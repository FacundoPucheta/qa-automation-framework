package com.facundo.automation.utils;

/**
 * Utility class for generating test data.
 */
public class TestDataUtils {
    public static long generateId(int base) {
        return base + (long) (Math.random() * 100);
    }
}
