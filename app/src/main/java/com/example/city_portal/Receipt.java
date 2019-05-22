package com.example.city_portal;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Receipt extends AppCompatActivity {

    ListView receiptListView;
    ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
    private SimpleAdapter sa;
    DatabaseHelper myDb;
    HashMap<String,String> item;     //Used to link data to lines
    Random random = new Random();
    int token =  random.nextInt(90) + 10;
    int appointment_no = random.nextInt(90000) + 10000;
    String appName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        receiptListView = findViewById(R.id.receipt_items);
        SharedPreferences prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        appName = prefs.getString("name", null);
        myDb = new DatabaseHelper(this);
        viewData();
    }

    private void viewData() {
        Cursor cursor = myDb.getReceiptAllData();
        Toast.makeText(Receipt.this,""+cursor.getCount(),Toast.LENGTH_SHORT).show();
        if (cursor.getCount() == 0) {
            Toast.makeText(getBaseContext(), "0 Data", Toast.LENGTH_SHORT).show();
        } else {

            while (cursor.moveToNext()) {
                this.item = new HashMap<String, String>();
                item.put("line1","TOKEN NO: N"+randToken());
                item.put("line2",cursor.getString(1));
                item.put("line3",appName);
                item.put("line4",cursor.getString(3)+" "+cursor.getString(4));
                item.put("line5",""+randAppointmentNo());
                item.put("line6",cursor.getString(2));
                list.add(item);
                sa = new SimpleAdapter(this, list, R.layout.receipt_layout, new String[]{"line1","line2", "line3", "line4", "line5", "line6"},
                        new int[]{R.id.edit_token, R.id.edit_name, R.id.name, R.id.edit_date, R.id.app_no, R.id.edit_service});
                receiptListView.setAdapter(sa);

            }
        }

    }

    private int randToken(){
        Random random = new Random();
        return random.nextInt(90) + 10;
    }

    private int randAppointmentNo(){
        Random random = new Random();
        return random.nextInt(9000000) + 1000000;
    }
}
