package com.example.restaurantrater;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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
        initSaveButton();
        initClearButton();
        initBackButton();
    }

    private void initSaveButton() {
        Button saveButton = findViewById(R.id.rateSaveButton);
        saveButton.setOnClickListener(v -> {
            dishRating = findViewById(R.id.ratingBar);
            EditText dishNameET = findViewById(R.id.dishNameEditText);
            EditText dishTypeET = findViewById(R.id.dishTypeEditText);
            currentDish.setDishRating(dishRating.getRating());
            currentDish.setRestaurantId(restaurantId);
            currentDish.setDishName(dishNameET.getText().toString());
            currentDish.setDishType(dishTypeET.getText().toString());

            RestaurantRaterDataSource ds = new RestaurantRaterDataSource(this);
            try {
                ds.open();
                if (currentDish.getDishId() == -1) {
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
                finish();
            }
        });
    }

    private void clear() {
        EditText dishNameET = findViewById(R.id.dishNameEditText);
        EditText dishTypeET = findViewById(R.id.dishTypeEditText);
        RatingBar ratingBar = findViewById(R.id.ratingBar);

        dishNameET.setText("");
        dishTypeET.setText("");
        ratingBar.setRating(0);
    }

    private void initClearButton() {
        Button clearButton = findViewById(R.id.rateClearButton);
        clearButton.setOnClickListener(v -> {
            clear();
        });
    }

    private void initBackButton() {
        Button backBtn = findViewById(R.id.rateBackButton);
        backBtn.setOnClickListener(v -> {
            clear();
            finish();
        });
    }

}