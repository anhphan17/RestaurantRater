package com.example.restaurantrater;

public class Dish {
    private int dishId;
    private String dishName;
    private String dishType;
    private float dishRating;
    private int restaurantId;

    public Dish() {
        dishId = -1;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int i) {
        dishId = i;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String s) {
        dishName = s;
    }

    private String getDishType() {
        return dishType;
    }

    private void setDishType(String s) {
        dishType = s;
    }

    private float getDishRating() {
        return dishRating;
    }

    private void setDishRating(float f) {
        dishRating = f;
    }

    private int getRestaurantId() {
        return restaurantId;
    }

    private void setRestaurantId(int i) {
        restaurantId = i;
    }

}
