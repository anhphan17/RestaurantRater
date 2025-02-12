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

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String s) {
        dishType = s;
    }

    public float getDishRating() {
        return dishRating;
    }

    public void setDishRating(float f) {
        dishRating = f;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int i) {
        restaurantId = i;
    }

}
