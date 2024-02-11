package com.example.moviestream;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.window.SplashScreen;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

public class NavigationActivity extends AppCompatActivity {

    YtsWrapperSingleton Yts=YtsWrapperSingleton.getInstance();
    RecyclerView recyclerView;
    RecyclierViewAdapter recyclierViewAdapter;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView imageMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        getWindow().setStatusBarColor(ContextCompat.getColor(NavigationActivity.this, R.color.shuhag));
        //Navigation Drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_View);
        imageMenu = findViewById(R.id.imageMenuId);
        //toolbar = findViewById(R.id.toolBarId);

        navigationView.bringToFront();
        navigationView.setCheckedItem(R.id.mHome);

        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.logoutId){
                    SharedPreferences preferences = getSharedPreferences(LoginActivity.preferenceName,0);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("hasLoggedIn",false);
                    editor.commit();
                    startActivity(new Intent(NavigationActivity.this,LoginActivity.class));
                    Toast.makeText(NavigationActivity.this, "Log Out", Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.nav_quality){
                    startActivity(new Intent(NavigationActivity.this, QualityActivity.class));
                }
                else if(id==R.id.notification){
                    startActivity(new Intent(NavigationActivity.this, NotificationActivity.class));
                }
                else if(id==R.id.favorite){
                    startActivity(new Intent(NavigationActivity.this, FavouriteActivity.class));
                }
                else if(id==R.id.mHome){
                    startActivity(new Intent(NavigationActivity.this,NavigationActivity.class));
                }
                else if(id==R.id.genre){
                    startActivity(new Intent(NavigationActivity.this,GenereActivity.class));
                }
                else if(id==R.id.sort){
                    startActivity(new Intent(NavigationActivity.this,Sort2Activity.class));
                }
                return true;
            }
        });
        recyclerView=findViewById(R.id.recyclerView);
        recyclierViewAdapter=new RecyclierViewAdapter(this);
        recyclerView.setAdapter(recyclierViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        update();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    update();
                }
            }
        });
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }
    static int page=1;
    public void update(){
        YtsWrapperSingleton.getInstance().get(page, new OnYtsFetch() {
            @Override
            public void onSuccess(JSONObject response) {
                try {
                    System.out.println(response.getJSONObject("data").getJSONArray("movies").length());
                    for(int i=0;i<response.getJSONObject("data").getJSONArray("movies").length();i++){
                        recyclierViewAdapter.add(response.getJSONObject("data").getJSONArray("movies").getJSONObject(i));
                        Log.d("movie _stream","MOVIE ADDED");
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            recyclierViewAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                page++;
            }

            @Override
            public void onFailed(Exception e) {
            }
        });
    }
    @Override
    public void onBackPressed(){
        //super.onBackPressed();
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            AlertDialog.Builder alertDialog  = new AlertDialog.Builder(NavigationActivity.this);
            alertDialog.setTitle("Warning!");
            alertDialog.setMessage("Do you want to exit ?");
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finishAffinity();
                }
            });
            alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            alertDialog.show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.preferenceName,0);
        boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn",false);

        if(!hasLoggedIn) {
            Intent intent = new Intent(NavigationActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}