package com.example.quanly;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.quanly.fragment.ComfirmFragment;
import com.example.quanly.fragment.DeliveryFragment;
import com.example.quanly.fragment.StatisticFragment;
import com.example.quanly.model.User;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private User user;
    private Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        drawerLayout =  findViewById(R.id.main_drawerlayout);
        toggle =  new ActionBarDrawerToggle(this, drawerLayout, toolbar , R.string.open , R.string.close );
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        drawerLayout.addDrawerListener(toggle);
        fragment = new ComfirmFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_framelayout , fragment).commit();

        NavigationView navigationView = findViewById(R.id.main_navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id  = item.getItemId();

                if (id == R.id.menu_statistic){
                    fragment = new StatisticFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("user", user);
                    fragment.setArguments(bundle);
                } else if (id == R.id.menu_comfirm_order) {
                    fragment = new ComfirmFragment();
                }else if (id == R.id.menu_comfirm_complete) {
                    fragment = new DeliveryFragment();
                }else if (id == R.id.menu_logout) {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
                if (fragment != null){
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_framelayout , fragment).commit();
                }

                // set title cho toolbar
                getSupportActionBar().setTitle(item.getTitle());
                drawerLayout.close();
                return true;

            }
        });
    }

    public User getUser(){
        return user;
    }
}