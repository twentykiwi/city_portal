package com.example.city_portal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText username, password;
    Button loginBtn, signupBtn;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        signupBtn = findViewById(R.id.signup);

        db = new DatabaseHelper(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = db.loginCheck(username.getText().toString(),password.getText().toString());
                if(result){
                    SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit();
                    editor.putString("name", username.getText().toString());
                    editor.apply();
                    Toast.makeText(login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                }
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doThis = new Intent(login.this, signup.class);
                startActivity(doThis);
            }
        });
    }
}
