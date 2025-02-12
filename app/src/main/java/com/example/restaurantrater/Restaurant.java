package com.example.restaurantrater;

public class Restaurant {
    private int restaurantId;
    private String restaurantName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipcode;

    public Restaurant() {
        restaurantId = -1;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int i) {
        restaurantId = i;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String s) {
        restaurantName = s;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String s) {
        streetAddress = s;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String s) {
        city = s;
    }

    public String getState() {
        return state;
    }

    public void setState(String s) {
        state = s;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String s) {
        zipcode = s;
    }



}
