package com.example.city_portal;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BookDoctorAppointment extends AppCompatActivity implements View.OnClickListener{

    EditText date;
    Button addBtn;
    private int mYear, mMonth, mDay;
    TextView name, service;
    String title, sub_title, dates, time;
    DatabaseAccess databaseAccess;
    int serviceValue, nameValue;
    DatabaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_doctor_appointment);
        //Get the bundle
        Bundle bundle = getIntent().getExtras();
        //Extract the data…
        serviceValue = bundle.getInt("stuff");
        nameValue = bundle.getInt("stff");
        myDb = new DatabaseHelper(this);

        date = (EditText) findViewById(R.id.bookDate);
        name = findViewById(R.id.bookName);
        service = findViewById(R.id.bookService);
        addBtn = findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (date.getText().toString().trim().length() > 0){
                    boolean isInserted =  myDb.insertReceiptData(
                            title,
                            sub_title,
                            dates,
                            time
                    );

                    if(isInserted = true)
                        Toast.makeText(BookDoctorAppointment.this,"Appoinment is successfully booked",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(BookDoctorAppointment.this,"Technical Error: Please try later",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(BookDoctorAppointment.this,"Please enter date!",Toast.LENGTH_SHORT).show();
            }
        });

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
                            dates = date.getText().toString();
                            time = getCurrentTime();

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

    }

    void viewData(){
        Cursor cursor1 = databaseAccess.getSelectedDoctorsData(serviceValue);
        Cursor cursor2 = databaseAccess.getSelectedHospitalData(nameValue);
        cursor1.moveToNext();
        cursor2.moveToNext();
        name.setText(cursor2.getString(1));
        service.setText(cursor1.getString(1));
        title = name.getText().toString();
        sub_title = "VISIT";
        databaseAccess.close();
    }

    public static String getCurrentTime() {
        //date output format
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }
}
