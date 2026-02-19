package com.facundo.automation.ui.models;

/**
 * Represents the data required to complete a purchase order form.
 */
public class OrderFormData {
    private final String name;
    private final String country;
    private final String city;
    private final String card;
    private final String month;
    private final String year;

    public OrderFormData(String name, String country, String city, String card, String month, String year) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.card = card;
        this.month = month;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getCard() {
        return card;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }
}
