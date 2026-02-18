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
     * Writes the given list of products into a text file.
     *
     * @param products {@link List} of products to be written
     */
    public static void writeProductsToFile(List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_PATH))) {

            for (Product product : products) {
                writer.write(product.toString());
                writer.newLine();
            }

        } catch (IOException ioException) {
            throw new RuntimeException("Error writing products to file", ioException);
        }
    }
}
