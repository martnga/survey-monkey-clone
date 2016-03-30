package com.craft.Survey;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mansa on 3/29/16.
 */
public class Login extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button mLoginBtn;
    ProgressDialog progressDialog;
    public static final String PREFS_NAME = "CREDENTIALS";
    public static String LOGIN_URL = AppController.URL + "/users/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(Login.this,R.style.AppTheme_Dark_Dialog);
        mEmail = (EditText) findViewById(R.id.email_txt);
        mPassword = (EditText) findViewById(R.id.password_txt);
        mLoginBtn = (Button) findViewById(R.id.btn_login);

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Init Progress Dialog
                //mLoginBtn.setEnabled(false);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();

               String email =  mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                loginUser(email, password);
            }
        });
    }

    public void loginUser(final String email, final String password){

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("email", email);
            jsonBody.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest authRequest = new JsonObjectRequest( Request.Method.POST,LOGIN_URL,jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Log.d("Login Class", response.toString());
                            String authToken = response.getString("authToken");
                            //saving credentials in shared preferences
                            SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("authToken", authToken);
                            editor.commit();
                            progressDialog.dismiss();
                            startActivity(new Intent(Login.this, MainActivity.class));
                           // finish();
                        }catch (Exception e){
                           progressDialog.dismiss();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Authentication error", "ERROR " + error.getCause());
                        progressDialog.dismiss();

                    }
                }){
            /*@Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password",password);
                Log.d("Login Class", params.toString());
                return params;
            }*/

           /* @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers =  new HashMap<>();
               // if(params==null)params = new HashMap<>();
               headers.put("Content-Type", "application/json");
                //..add other headers
                return headers;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }*/
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(authRequest);

    }

}

