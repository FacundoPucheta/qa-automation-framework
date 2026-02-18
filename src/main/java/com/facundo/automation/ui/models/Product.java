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
        this.price = sanitizePrice(price);
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
     * Strips currency symbols and whitespace from raw price strings. ("$360 " => "360")
     */
    private String sanitizePrice(String rawPrice) {
        if (rawPrice == null) return "";
        return rawPrice.replaceAll("[^\\d.]", "").trim();
    }

    /**
     * Returns a formatted representation of the product suitable for text file export.
     */
    @Override
    public String toString() {
        return String.format("Name: %s | Price: %s | Link: %s", name, price, link);
    }
}