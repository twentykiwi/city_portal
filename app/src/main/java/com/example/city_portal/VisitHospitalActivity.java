package com.example.city_portal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class VisitHospitalActivity extends AppCompatActivity {

    ImageView img;
    TextView name, mobile, address, opening, closing;
    Button appBtn;
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
        appBtn = findViewById(R.id.appBtn);

        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        SharedPreferences prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        final String nameSP = prefs.getString("name", null);

        viewData();

        appBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameSP != null){

                    String time = getCurrentTime();
                    Toast.makeText(VisitHospitalActivity.this,"Your appointment is booked at "+time,Toast.LENGTH_SHORT).show();
                    Intent doThis = new Intent(VisitHospitalActivity.this,MainActivity.class);
                    startActivity(doThis);

                }else {
                    Intent doThis = new Intent(VisitHospitalActivity.this, login.class);
                    startActivity(doThis);
                }
            }
        });
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

    public static String getCurrentTime() {
        //date output format
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }
}
