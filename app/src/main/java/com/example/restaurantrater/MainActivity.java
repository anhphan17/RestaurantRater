package com.example.restaurantrater;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Restaurant currentRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initSaveButton();
        initRateButton();
        initCancelButton();

        currentRestaurant = new Restaurant();
    }

    private void initSaveButton() {
        Button saveBtn = findViewById(R.id.mainSaveButton);
        saveBtn.setOnClickListener(v -> {
            EditText restaurantNameET = findViewById(R.id.restaurantNameEditText);
            EditText streetAddressET = findViewById(R.id.editAddress);
            EditText cityET = findViewById(R.id.editCity);
            EditText stateET = findViewById(R.id.editState);
            EditText zipcodeET = findViewById(R.id.editZipcode);

            currentRestaurant.setRestaurantName(restaurantNameET.getText().toString());
            currentRestaurant.setStreetAddress(streetAddressET.getText().toString());
            currentRestaurant.setCity(cityET.getText().toString());
            currentRestaurant.setState(stateET.getText().toString());
            currentRestaurant.setZipcode(zipcodeET.getText().toString());

            boolean didSucceed;
            RestaurantRaterDataSource ds = new RestaurantRaterDataSource(MainActivity.this);
            try {
                ds.open();

                if (currentRestaurant.getRestaurantId() == -1) {
                    didSucceed = ds.insertRestaurant(currentRestaurant);
                    if (didSucceed) {
                        int newRestaurantId = ds.getLastRestaurantId();
                        currentRestaurant.setRestaurantId(newRestaurantId);
                    }
                }
                else {
                    ds.updateRestaurant(currentRestaurant);
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

    private void initRateButton() {
        Button rateButton = findViewById(R.id.rateButton);
        rateButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RateDishActivity.class);
            intent.putExtra("restaurant_id", currentRestaurant.getRestaurantId());
            startActivity(intent);
        });
    }

    private void initCancelButton() {
        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> {
            EditText restaurantNameET = findViewById(R.id.restaurantNameEditText);
            EditText streetAddressET = findViewById(R.id.editAddress);
            EditText cityET = findViewById(R.id.editCity);
            EditText stateET = findViewById(R.id.editState);
            EditText zipcodeET = findViewById(R.id.editZipcode);

            restaurantNameET.setText("");
            streetAddressET.setText("");
            cityET.setText("");
            stateET.setText("");
            zipcodeET.setText("");
        });
    }
}