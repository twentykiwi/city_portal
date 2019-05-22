package com.example.city_portal;

import android.content.Intent;
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

public class Beautician extends AppCompatActivity {

    ListView beauticianListView;
    ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
    private SimpleAdapter sa;
    DatabaseAccess databaseAccess;
    HashMap<String,String> item;     //Used to link data to lines
    int value;
    int ids[] = new int[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beautician);

        //Get the bundle
        Bundle bundle = getIntent().getExtras();
        //Extract the data…
        value = bundle.getInt("stuff");

        beauticianListView = findViewById(R.id.beautician);
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        viewData();


        beauticianListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Beautician.this, BookBeautyAppointment.class);

                //Create the bundle
                Bundle bundle = new Bundle();

                //Add your data to bundle
                bundle.putInt("stuff", ids[position]);
                bundle.putInt("stff",value);

                //Add the bundle to the intent
                i.putExtras(bundle);

                //Fire that second activity
                startActivity(i);
            }
        });
    }

    private void viewData() {
        int i = 0;
        Cursor cursor = databaseAccess.getSelectedBeautyServicesData(value);
        if (cursor.getCount() == 0) {
            Toast.makeText(getBaseContext(), "0 Data", Toast.LENGTH_SHORT).show();
        } else {

            while (cursor.moveToNext()) {
                this.item = new HashMap<String, String>();
                item.put("line1", cursor.getString(1));
                item.put("line2","\u20B9 "+ cursor.getString(2));
                list.add(item);
                sa = new SimpleAdapter(this, list, R.layout.two_line, new String[]{"line1", "line2"},
                        new int[]{R.id.nameEdit, R.id.rateEdit});
                beauticianListView.setAdapter(sa);
                ids[i] = cursor.getInt(0);
                i++;

            }
        }
        databaseAccess.close();

    }
}
