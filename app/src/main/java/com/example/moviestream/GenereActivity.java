package com.example.moviestream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class GenereActivity extends AppCompatActivity {
    RadioButton radioButton,radioButton2,radioButton3,radioButton4;
    RadioButton radioButton5,radioButton6,radioButton10,radioButton7,radioButton9,radioButton11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genere);
        getWindow().setStatusBarColor(ContextCompat.getColor(GenereActivity.this, R.color.shuhag));

        radioButton = findViewById(R.id.popular);
        radioButton2 =findViewById(R.id.advanture);
        radioButton3 =findViewById(R.id.action);
        radioButton4 =findViewById(R.id.animation);
        radioButton5 =findViewById(R.id.comedy);
        radioButton6 =findViewById(R.id.crime);
        radioButton7 =findViewById(R.id.documentary);
        radioButton9=findViewById(R.id.family);
        radioButton10 =findViewById(R.id.film_noir);
        radioButton11 =findViewById(R.id.history);


        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GenereActivity.this,NavigationActivity.class);
                startActivity(intent);


            }
        });
        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GenereActivity.this,NavigationActivity.class);
                startActivity(intent);


            }
        });
        radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GenereActivity.this,NavigationActivity.class);
                startActivity(intent);


            }
        });
        radioButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GenereActivity.this,NavigationActivity.class);
                startActivity(intent);


            }
        });
        radioButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GenereActivity.this,NavigationActivity.class);
                startActivity(intent);


            }
        });
        radioButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GenereActivity.this,NavigationActivity.class);
                startActivity(intent);


            }
        });
        radioButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GenereActivity.this,NavigationActivity.class);
                startActivity(intent);


            }
        });

        radioButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GenereActivity.this,NavigationActivity.class);
                startActivity(intent);


            }
        });
        radioButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GenereActivity.this,NavigationActivity.class);
                startActivity(intent);


            }
        });
        radioButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GenereActivity.this,NavigationActivity.class);
                startActivity(intent);


            }
        });


    }
}