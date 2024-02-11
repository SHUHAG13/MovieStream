package com.example.moviestream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;

public class RecyclierViewAdapter extends RecyclerView.Adapter<RecyclierViewAdapter.Myviewholder> {
    Vector<JSONObject> data=new Vector<>();
    Activity cn;
    public RecyclierViewAdapter(Activity c) {
        cn=c;
    }

    public void add(JSONObject mv){
        this.data.add(mv);
        Log.d("II_WATU_1", String.valueOf(this.data.size()));
    }
    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.imagel_layout,parent,false);
        return new Myviewholder(v);
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        try {
            String name=data.get(position).getString("title");
            String des=data.get(position).getString("summary");
            String img=data.get(position).getString("medium_cover_image");
            String rat=data.get(position).getString("rating");
            holder.setT(name);
            holder.setRating(rat);
            holder.url=img;
            try {
                holder.s.setText(des.substring(0, Math.min(des.length() - 1, 100)) + "...");
                holder.od=des;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap b=getBitmapFromURL(img);
                        cn.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                holder.i.setImageBitmap(b);
                            }
                        });
                    }
                }).start();
            }catch (Exception e){
                holder.s.setText(e.getLocalizedMessage());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        Log.d("II_WATU", String.valueOf(data.size()));
        return data.size();
    }
    public class Myviewholder extends RecyclerView.ViewHolder {
        public TextView t,s;
        public ImageView i;
        public View v;
        public String url,od;
        public String rating;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            v=itemView;
            t=itemView.findViewById(R.id.title);
            i=itemView.findViewById(R.id.img);
            s=itemView.findViewById(R.id.des);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(cn,MovieDetails.class);
                    intent.putExtra("title",t.getText());
                    intent.putExtra("description",od);
                    intent.putExtra("rating",rating);
                    intent.putExtra("image",url);
                    cn.startActivity(intent);
                }
            });
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public TextView getT() {
            return t;
        }

        public void setT(String title) {
            t.setText(title);
        }

        public ImageView getI() {
            return i;
        }

        public void setI(ImageView i) {
            this.i = i;
        }
    }
}
