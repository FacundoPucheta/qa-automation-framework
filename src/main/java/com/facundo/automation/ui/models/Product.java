package com.facundo.automation.ui.models;

/**
 * Represents a product extracted from the UI.
 */
public class Product {
    private final String name;
    private final String price;
    private final String link;

    public Product(String name, String price, String link) {
        this.name = name;
        this.price = price;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getLink() {
        return link;
    }

    /**
     * Returns a formatted representation of the product suitable for text file export.
     */
    @Override
    public String toString() {
        return String.format("Name: %s | Price: %s | Link: %s", name, price, link);
    }
}