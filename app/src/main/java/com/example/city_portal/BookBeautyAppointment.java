package com.example.city_portal;

import android.app.DatePickerDialog;
import java.util.Calendar;
import java.util.HashMap;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class BookBeautyAppointment extends AppCompatActivity implements View.OnClickListener {

    EditText date;
    private int mYear, mMonth, mDay;
    TextView name, service;
    DatabaseAccess databaseAccess;
    int serviceValue, nameValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_beauty_appointment);
        //Get the bundle
        Bundle bundle = getIntent().getExtras();
        //Extract the data…
        serviceValue = bundle.getInt("stuff");
        nameValue = bundle.getInt("stff");

        Toast.makeText(BookBeautyAppointment.this,""+serviceValue,Toast.LENGTH_SHORT).show();

        date = (EditText) findViewById(R.id.bookDate);
        name = findViewById(R.id.bookName);
        service = findViewById(R.id.bookService);
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        viewData();

        date.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == date) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

    }

    void viewData(){
        Cursor cursor1 = databaseAccess.getSelectedServicesData(serviceValue);
        Cursor cursor2 = databaseAccess.getSelectedBeautyData(nameValue);
        cursor1.moveToNext();
        cursor2.moveToNext();
        name.setText(cursor2.getString(1));
        service.setText(cursor1.getString(1));
        databaseAccess.close();
    }
}
