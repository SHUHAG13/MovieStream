package com.example.moviestream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;


public class Sort2Activity extends AppCompatActivity {
    RadioButton popular, date,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort2);
        getWindow().setStatusBarColor(ContextCompat.getColor(Sort2Activity.this, R.color.shuhag));


        popular=findViewById(R.id.pop);
        date = findViewById(R.id.date);
        year = findViewById(R.id.year);

        popular.setOnClickListener(view -> {
            startActivity(new Intent(this, NavigationActivity.class));
        });
        date.setOnClickListener(view -> {
            startActivity(new Intent(this,NavigationActivity.class));

        });
        year.setOnClickListener(view -> {
            startActivity(new Intent(this,NavigationActivity.class));

        });
    }
}