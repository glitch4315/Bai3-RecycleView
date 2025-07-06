package com.example.foodapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class DetailActivity extends AppCompatActivity {

    ImageView detailImageView;
    TextView detailTextView;
    TextView detailDescriptionTextView;
    TextView detailPriceTextView;
    Button orderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailImageView = findViewById(R.id.detailImageView);
        detailTextView = findViewById(R.id.detailTextView);
        detailDescriptionTextView = findViewById(R.id.detailDescriptionTextView);
        detailPriceTextView = findViewById(R.id.detailPriceTextView);
        orderButton = findViewById(R.id.orderButton);

        Food food = getIntent().getParcelableExtra("FoodItem");

        if (food != null) {
            detailTextView.setText(food.getName());
            detailDescriptionTextView.setText(food.getDescription());
            detailPriceTextView.setText("Giá: " + food.getPrice() + " VNĐ");
            detailImageView.setImageResource(food.getImageResource());


            SharedPreferences prefs = getSharedPreferences("FoodPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("last_viewed_food", food.getName());
            editor.apply();

            orderButton.setOnClickListener(v -> {
                SharedPreferences pref = getSharedPreferences("FoodPrefs", Context.MODE_PRIVATE);
                Set<String> orderedFoods = pref.getStringSet("ordered_foods", new HashSet<>());

                Set<String> updatedFoods = new HashSet<>(orderedFoods);
                updatedFoods.add(food.getName());

                SharedPreferences.Editor ed = pref.edit();
                ed.putStringSet("ordered_foods", updatedFoods);
                ed.apply();

                Toast.makeText(this, "Đã gọi món: " + food.getName(), Toast.LENGTH_SHORT).show();
            });
        }
    }
}
