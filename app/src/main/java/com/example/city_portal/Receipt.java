package com.example.city_portal;

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

public class Receipt extends AppCompatActivity {

    ListView receiptListView;
    ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
    private SimpleAdapter sa;
    DatabaseHelper myDb;
    HashMap<String,String> item;     //Used to link data to lines


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        receiptListView = findViewById(R.id.receipt_items);
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
                item.put("line1",cursor.getString(1));
                item.put("line2",cursor.getString(2));
                item.put("line3","DATE: "+cursor.getString(3));
                item.put("line4","TIME: "+cursor.getString(4));
                list.add(item);
                sa = new SimpleAdapter(this, list, R.layout.receipt_layout, new String[]{"line1", "line2", "line3" ,"line4"},
                        new int[]{R.id.edit_name, R.id.edit_spec,R.id.edit_date, R.id.edit_time});
                receiptListView.setAdapter(sa);

            }
        }

    }
}
