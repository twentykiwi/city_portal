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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {
    private EditText name,phoneno,username,password,confirmPass;
    private Button btn_register;
    private static  String URL_REGIST="http://123.0.0.1:80/city_portal/user1.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name=findViewById(R.id.name);
        phoneno=findViewById(R.id.phoneno);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        btn_register=findViewById(R.id.btn_regist);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();

            }
        });


    }
    private  void Regist(){
        btn_register.setVisibility(View.GONE);

        final String name = this.name.getText().toString().trim();
        final String phoneno = this.name.getText().toString().trim();
        final String username = this.name.getText().toString().trim();
        final String password= this.name.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject= new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if(success.equals("1")){
                        Toast.makeText(signup.this, "Register success!!", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(signup.this, "Register error!!"+e.toString(), Toast.LENGTH_LONG).show();
                    btn_register.setVisibility(View.VISIBLE);
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(signup.this, "Register error!!"+error.toString(), Toast.LENGTH_LONG).show();
                        btn_register.setVisibility(View.VISIBLE);

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();
                params.put("name",name);
                params.put("phone",phoneno);
                params.put("username",username);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
