package com.craft.Survey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.github.florent37.viewanimator.ViewAnimator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.grabner.circleprogress.AnimationState;
import at.grabner.circleprogress.AnimationStateChangedListener;
import at.grabner.circleprogress.CircleProgressView;
import at.grabner.circleprogress.TextMode;
import at.grabner.circleprogress.UnitPosition;

public class MainActivity extends AppCompatActivity {

    TextView mealbtn,general,Intro;
    public static final String PREFS_NAME = "CREDENTIALS";
    public static final String TAG = "MainActivityClass";
    public static String GENERAL_STAY_URL = AppController.URL + "/questions/survey/2";
    public static String MEAL_URL = AppController.URL + "/questions/survey/1";
    public static String KEY_AUTH_TOKEN = "X-Auth-Token";

    public static HashMap<String, String>  GeneralStayQuestions = new HashMap<>();
    public static HashMap<String, String>  MealQuestions = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!isCurrentUser()){
            startActivity(new Intent(MainActivity.this, Login.class));
        }

        setContentView(R.layout.activity_main);
        /*final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);*/
        mealbtn=(TextView) findViewById(R.id.mealbtn);
        general=(TextView) findViewById(R.id.general);
        Intro=(TextView) findViewById(R.id.Intro);
        Typeface CustomFont=Typeface.createFromAsset(getAssets(),"fonts/SourceSerifPro-Regular.ttf");
        Intro.setTypeface(CustomFont);
        Typeface ButtonFont=Typeface.createFromAsset(getAssets(),"fonts/SourceSerifPro-Bold.ttf");
        mealbtn.setTypeface(ButtonFont);
        general.setTypeface(ButtonFont);

        FetchGeneralStayQuestions();
        FetchMealQuestions();

        mealbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Meal.class);
                startActivity(intent);
            }
        });
        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,GeneralStay.class);
                startActivity(intent);
            }
        });
    }


    public boolean isCurrentUser(){

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        final String authToken = sharedPreferences.getString("authToken", "");

        if(authToken == null || authToken.isEmpty() || authToken.length() < 0){
            return false;
        }

        return true;
    }


    //Fetching General Stay Questions
    public void FetchGeneralStayQuestions(){

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        final String authToken = sharedPreferences.getString("authToken", "");

        /*JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("authToken", authToken);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        JsonObjectRequest authRequest = new JsonObjectRequest( Request.Method.GET,GENERAL_STAY_URL,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Log.d(TAG, response.toString());
                            JSONArray questions = response.getJSONArray("questions");
                            for( int i = 0; i < questions.length(); i++){
                                JSONObject obj = questions.getJSONObject(i);

                                GeneralStayQuestions.put(obj.getString("id"), obj.getString("textContent"));
                            }

                            //progressDialog.dismiss();
                            // startActivity(new Intent(GeneralStay.this, MainActivity.class));
                            // finish();
                        }catch (Exception e){
                            //progressDialog.dismiss();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("GenralStay Fetch error", "ERROR " + error.getCause());
                        //progressDialog.dismiss();

                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put(KEY_AUTH_TOKEN, authToken);
                return headers;
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(authRequest);

    }

    //Fetching Meal Questions
    public void FetchMealQuestions(){

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        final String authToken = sharedPreferences.getString("authToken", "");

        /*JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("authToken", authToken);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        JsonObjectRequest authRequest = new JsonObjectRequest( Request.Method.GET,MEAL_URL,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Log.d(TAG, response.toString());
                            JSONArray questions = response.getJSONArray("questions");
                            for( int i = 0; i < questions.length(); i++){
                                JSONObject obj = questions.getJSONObject(i);
                                MealQuestions.put(obj.getString("id"), obj.getString("textContent"));
                            }
                            //progressDialog.dismiss();
                            // startActivity(new Intent(GeneralStay.this, MainActivity.class));
                            // finish();
                        }catch (Exception e){
                            //progressDialog.dismiss();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Meal Fetching error", "ERROR " + error.getCause());
                        //progressDialog.dismiss();

                    }
                }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put(KEY_AUTH_TOKEN, authToken);
                return headers;
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(authRequest);

    }
}
