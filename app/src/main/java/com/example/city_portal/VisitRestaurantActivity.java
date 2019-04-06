package com.example.city_portal;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class VisitRestaurantActivity extends AppCompatActivity {

    ImageView img;
    TextView name, mobile, address, tables;
    int value;
    DatabaseAccess databaseAccess;
    int[] img_path = {R.drawable.rest1,R.drawable.rest2,R.drawable.rest3,R.drawable.rest4,R.drawable.rest5,R.drawable.rest5,R.drawable.rest5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_restaurant);
        //Get the bundle
        Bundle bundle = getIntent().getExtras();
        //Extract the data…
        value = bundle.getInt("stuff");

        img = findViewById(R.id.item_image);
        name = findViewById(R.id.item_name);
        mobile = findViewById(R.id.item_mobile);
        address = findViewById(R.id.item_address);
        tables = findViewById(R.id.item_open_time);

        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        viewData();
    }

    private void viewData(){
        Cursor cursor = databaseAccess.getSelectedRestaurantData(value+1);
        cursor.moveToNext();
        img.setImageResource(img_path[value]);
        name.setText(cursor.getString(1));
        mobile.setText("Phone No. :"+cursor.getString(2));
        address.setText("Address: "+cursor.getString(4));
        //tables.setText("No. of tables: "+cursor.getString(3));

        databaseAccess.close();

    }
}
