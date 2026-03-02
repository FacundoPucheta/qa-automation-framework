package com.facundo.automation.ui.utils;

import com.facundo.automation.ui.models.Product;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Utility class for writing product data into a text file.
 */
public class FileUtils {
    private static final String OUTPUT_PATH = "target/products.txt";

    /**
     * Writes the provided list of products into a CSV file.
     * <p>
     * Format: name,price,link
     *
     * @param products {@link List} of {@link Product} to export
     */
    public static void writeProductsToCsv(List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_PATH))) {
            writer.write("name,price,link");
            writer.newLine();

            for (Product product : products) {
                writer.write(formatProduct(product));
                writer.newLine();
            }

        } catch (IOException ioException) {
            throw new RuntimeException("Error writing products to CSV file", ioException);
        }
    }

    /**
     * Formats a product into a valid CSV row.
     */
    private static String formatProduct(Product product) {
        return sanitizeCsvValue(product.getName()) + "," +
                sanitizeCsvValue(product.getPrice()) + "," +
                sanitizeCsvValue(product.getLink());
    }

    /**
     * Sanitizes a value for safe CSV inclusion by escaping commas and quotes.
     */
    private static String sanitizeCsvValue(String value) {
        if (value == null) return "";

        if (value.contains(",") || value.contains("\"")) {
            value = value.replace("\"", "\"\"");
            return "\"" + value + "\"";
        }

        return value;
    }
}
