package com.example.city_portal;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class VisitActivity extends AppCompatActivity {
    ImageView img;
    TextView name, mobile, address, opening, closing;
    int value;
    DatabaseAccess databaseAccess;
    int[] img_path = {R.drawable.beauty1,R.drawable.beauty2,R.drawable.beauty3,R.drawable.beauty4,R.drawable.beauty5,R.drawable.beauty6,R.drawable.beauty7,R.drawable.beauty8,R.drawable.beauty9,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);
        //Get the bundle
        Bundle bundle = getIntent().getExtras();
        //Extract the data…
        value = bundle.getInt("stuff");

        img = findViewById(R.id.item_image);
        name = findViewById(R.id.item_name);
        mobile = findViewById(R.id.item_mobile);
        address = findViewById(R.id.item_address);
        opening = findViewById(R.id.item_open_time);
        closing = findViewById(R.id.item_close_time);

        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        viewData();
    }

    private void viewData(){
        Cursor cursor = databaseAccess.getSelectedBeautyData(value+1);
        cursor.moveToNext();
        img.setImageResource(img_path[value]);
        name.setText(cursor.getString(1));
        mobile.setText(cursor.getString(2));
        address.setText(cursor.getString(3));
        opening.setText(cursor.getString(4));
        closing.setText(cursor.getString(5));

        databaseAccess.close();

    }
}
