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

public class restaurant extends AppCompatActivity {

    ListView restaurantListView;
    ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
    private SimpleAdapter sa;
    DatabaseAccess databaseAccess;
    HashMap<String,String> item;     //Used to link data to lines

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        restaurantListView = findViewById(R.id.restaurantListView);
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        viewData();

        restaurantListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(restaurant.this, VisitRestaurantActivity.class);

                //Create the bundle
                Bundle bundle = new Bundle();

                //Add your data to bundle
                bundle.putInt("stuff", position);

                //Add the bundle to the intent
                i.putExtras(bundle);

                //Fire that second activity
                startActivity(i);
            }
        });
    }

    private void viewData() {
        Cursor cursor = databaseAccess.getRestaurantData();
        if (cursor.getCount() == 0) {
            Toast.makeText(getBaseContext(), "0 Data Entered", Toast.LENGTH_SHORT).show();
        } else {

            while (cursor.moveToNext()) {
                this.item = new HashMap<String, String>();
                item.put("line1", cursor.getString(1));
                item.put("line2", "PHONE: " + cursor.getString(2));
                item.put("line3", "ADD: " + cursor.getString(4));
                list.add(item);
                sa = new SimpleAdapter(this, list, R.layout.multi_lines, new String[]{"line1", "line2", "line3"},
                        new int[]{R.id.edit_name, R.id.edit_mobile, R.id.edit_address});
                restaurantListView.setAdapter(sa);


            }
        }
        databaseAccess.close();

    }
}
