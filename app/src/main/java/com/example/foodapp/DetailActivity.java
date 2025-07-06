package com.example.foodapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

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
            detailImageView.setImageResource(food.getImageResId());
            detailTextView.setText(food.getName());
            detailDescriptionTextView.setText(food.getDescription());
            detailPriceTextView.setText(String.format("%.0f VND", food.getPrice()));

            orderButton.setOnClickListener(v ->
                    Toast.makeText(this, "Đặt hàng thành công! " + food.getName(), Toast.LENGTH_SHORT).show()
            );
        } else {
            Toast.makeText(this, "Không có dữ liệu món ăn!", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if no data is passed
        }
    }
}
