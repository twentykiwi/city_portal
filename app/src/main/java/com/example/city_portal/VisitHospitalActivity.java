package com.example.city_portal;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class VisitHospitalActivity extends AppCompatActivity {

    ImageView img;
    TextView name, mobile, address, opening, closing;
    int value;
    DatabaseAccess databaseAccess;
    int[] img_path = {R.drawable.hospital1,R.drawable.hospital2,R.drawable.hospital3,R.drawable.hospital4,R.drawable.hospital5,R.drawable.hospital6,R.drawable.hospital6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_hospital);
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
        Cursor cursor = databaseAccess.getSelectedHospitalData(value+1);
        cursor.moveToNext();
        img.setImageResource(img_path[value]);
        name.setText(cursor.getString(1));
        mobile.setText("Phone No. :"+cursor.getString(2));
        address.setText("Address: "+cursor.getString(3));

        databaseAccess.close();

    }
}
