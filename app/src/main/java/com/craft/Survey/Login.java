package com.craft.Survey;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mansa on 3/29/16.
 */
public class Login extends AppCompatActivity {

    EditText mEmail, mPassword;
    public static String LOGIN_URL = "http://196.43.248.17:8080/afex/api/users/login";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = (EditText) findViewById(R.id.email_txt);
        mPassword = (EditText) findViewById(R.id.password_txt);

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String email =  mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                loginUser(email, password);
            }
        });
    }

    public void loginUser(final String email, final String password){
        JsonObjectRequest authRequest = new JsonObjectRequest(Request.Method.POST, LOGIN_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Log.d("Login Class", response.toString());
                            /*//saving credentials in shared preferences
                            SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("phoneNumber", getPhoneNumber());
                            editor.putString("authToken", (String) response.get("token"));
                            editor.commit();*/
                           // finish();
                        }catch (Exception e){}

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Authentication error", error.getMessage());

                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password",password);
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(authRequest);

    }

}

