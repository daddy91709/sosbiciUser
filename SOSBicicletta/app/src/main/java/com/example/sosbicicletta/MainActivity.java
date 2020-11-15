package com.example.sosbicicletta;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.sosbicicletta.WebActivity.CS;
import com.example.sosbicicletta.WebActivity.Mb;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    
    private static int SPALSH_TIME_OUT = 2000;
    private Toolbar toolbar,toolbartab;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private PageAdapter pageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbartab = (Toolbar)findViewById(R.id.toolbartab);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        pageAdapter = new PageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new MappaFragment(),"");
        pageAdapter.addFragment(new PercorsiFragment(),"");
        pageAdapter.addFragment(new ContattiFragment(),"");

        viewPager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.getTabAt(0).setIcon(R.drawable.baseline_near_me_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.baseline_place_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.baseline_local_phone_black_24dp);

        viewPager.setPageTransformer(true,new DepthPageTransformer());



//NavigationMenu
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
//NavigationMenu start
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState == null){

            getSupportFragmentManager().beginTransaction().replace(R.id.viewpager,new MappaFragment()).commit();
            navigationView.setCheckedItem(R.id.mapview);


        }





    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
        int id=Item.getItemId();
        switch (id){

            case R.id.chisiamoM:
                startActivity(new Intent(MainActivity.this, CS.class));
                break;
            case R.id.mobeM:
                startActivity(new Intent(MainActivity.this, Mb.class));
                break;
        }

         drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}
