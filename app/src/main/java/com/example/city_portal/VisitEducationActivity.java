package com.example.city_portal;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class VisitEducationActivity extends AppCompatActivity {
    ImageView img;
    TextView name, mobile, address;
    int value;
    DatabaseAccess databaseAccess;
    int[] img_path = {R.drawable.school6, R.drawable.school2, R.drawable.school3, R.drawable.school1, R.drawable.school2, R.drawable.school5, R.drawable.school4,R.drawable.school4,R.drawable.school5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_education);
        //Get the bundle
        Bundle bundle = getIntent().getExtras();
        //Extract the data…
        value = bundle.getInt("stuff");

        img = findViewById(R.id.item_image);
        name = findViewById(R.id.item_name);
        mobile = findViewById(R.id.item_mobile);
        address = findViewById(R.id.item_address);

        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        viewData();
    }

    private void viewData() {
        Cursor cursor = databaseAccess.getSelectedEducationData(value + 1);
        cursor.moveToNext();
        img.setImageResource(img_path[value]);
        name.setText(cursor.getString(1));
        mobile.setText("Phone No. :" + cursor.getString(2));
        address.setText("Address: " + cursor.getString(4));

        databaseAccess.close();

    }
}
