package com.example.restaurantrater;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RateDishActivity extends AppCompatActivity {


    private int restaurantId;

    private Dish currentDish;

    private RatingBar dishRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rate_dish);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        restaurantId = intent.getIntExtra("restaurant_id", -1);
        currentDish = new Dish();
        initRatingBar();
        initSaveButton();
    }

    private void initRatingBar() {
        dishRating = findViewById(R.id.ratingBar);
    }

    private void initSaveButton() {
        Button saveButton = findViewById(R.id.rateSaveButton);
        saveButton.setOnClickListener(v -> {
            currentDish.setDishRating(dishRating.getRating());
            currentDish.setRestaurantId(restaurantId);

            RestaurantRaterDataSource ds = new RestaurantRaterDataSource(this);
            try {
                ds.open();
                if (currentDish.getRestaurantId() == -1) {
                    boolean wasInserted = ds.insertDish(currentDish);
                    if (wasInserted) {
                        int newDishId = ds.getLastDishId();
                        currentDish.setDishId(newDishId);
                    }
                }
                else {
                    ds.updateDish(currentDish);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                ds.close();
            }
        });
    }
}