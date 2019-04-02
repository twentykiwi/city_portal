package com.example.city_portal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public void login1(View view)
    {
        Intent i=new Intent(MainActivity.this,login.class);
        startActivity(i);
    }
    public void beauty(View view)
    {
        Intent i=new Intent(MainActivity.this,beauty.class);
        startActivity(i);
    }
    public void education(View view)
    {
        Intent i=new Intent(MainActivity.this,education.class);
        startActivity(i);
    }
    public void hospital(View view)
    {
        Intent i=new Intent(MainActivity.this,hospital.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id=item.getItemId();
       if(id==R.id.nav_restaurant)
       {
           Intent obj= new Intent(this,restaurant.class);
           startActivity(obj);
           Toast.makeText(this,"restaurant",Toast.LENGTH_SHORT).show();
       }
        if(id==R.id.nav_hospital)
        {
            Intent obj= new Intent(this,hospital.class);
            startActivity(obj);
            Toast.makeText(this,"Hospital",Toast.LENGTH_SHORT).show();
        }
        if(id==R.id.nav_education)
        {
            Intent obj= new Intent(this,education.class);
            startActivity(obj);
            Toast.makeText(this,"Education",Toast.LENGTH_SHORT).show();
        }
        if(id==R.id.nav_beauty)
        {
            Intent obj= new Intent(this,beauty.class);
            startActivity(obj);
            Toast.makeText(this,"Beauty",Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}
