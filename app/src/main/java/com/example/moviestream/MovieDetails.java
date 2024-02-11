package com.example.moviestream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MovieDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        getWindow().setStatusBarColor(ContextCompat.getColor(MovieDetails.this, R.color.shuhag));

        TextView rat=findViewById(R.id.movie_ratting);
        ImageView im=findViewById(R.id.thumb);
        TextView ti=findViewById(R.id.movieTitle);
        TextView des=findViewById(R.id.movie_discription);
        String ratting=getIntent().getStringExtra("rating");
        String img=getIntent().getStringExtra("image");

        String title=getIntent().getStringExtra("title");
        String description=getIntent().getStringExtra("description");
        rat.setText("Rating : "+ratting);
        ti.setText("NAME : "+title);
        des.setText("MOVIE DESCRIPTION  : "+description);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap b=RecyclierViewAdapter.getBitmapFromURL(img);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        im.setImageBitmap(b);
                    }
                });
            }
        }).start();
    }
}