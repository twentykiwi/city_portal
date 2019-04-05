package com.example.city_portal;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {
    private EditText name,phoneno,username,password,confirmPass;
    private Button btn_register;
    AwesomeValidation awesomeValidation;
    DatabaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        myDb = new DatabaseHelper(this);

        updateUI();



    }

    private void updateUI(){
        name=findViewById(R.id.name);
        phoneno=findViewById(R.id.phoneno);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        btn_register=findViewById(R.id.btn_regist);

        // to validate the confirmation of another field
        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";

        awesomeValidation.addValidation(signup.this, R.id.name, "[a-zA-Z\\s]+", R.string.name_err);
        awesomeValidation.addValidation(signup.this, R.id.phoneno, RegexTemplate.TELEPHONE, R.string.mobile_err);
        awesomeValidation.addValidation(signup.this, R.id.username, "[A-Za-z0-9_]+", R.string.username_err);
        awesomeValidation.addValidation(signup.this, R.id.password, regexPassword, R.string.password_err);
        awesomeValidation.addValidation(signup.this, R.id.confirm_password, R.id.password, R.string.confirm_password_err);

        btn_register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()){
                    boolean isInserted =  myDb.insertData(
                            name.getText().toString(),
                            phoneno.getText().toString(),
                            username.getText().toString(),
                            password.getText().toString()
                    );
                    if(isInserted = true)
                        Toast.makeText(signup.this,"New Member Signup Successful",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(signup.this,"Technical Error: Please try later",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(signup.this,"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
