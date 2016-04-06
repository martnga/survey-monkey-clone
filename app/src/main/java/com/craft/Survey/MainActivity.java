package com.craft.Survey;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    TextView mealbtn,general,Intro;
    public static final String PREFS_NAME = "CREDENTIALS";
    public static final String TAG = "MainActivityClass";

    public static HashMap<String, String> GeneralStayQuestions = new HashMap<>();
    public static HashMap<String, String>  MealQuestions = new HashMap<>();

    public static HashMap<String, Boolean>  GnrlStayQstnsIfRatings = new HashMap<>();
    public static HashMap<String, Boolean>  MealQstnsIfRatings = new HashMap<>();

    public static String GENERAL_STAY_URL = AppController.URL + "/questions/survey/2";
    public static String MEAL_URL = AppController.URL + "/questions/survey/1";
    public static String KEY_AUTH_TOKEN = "X-Auth-Token";


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
        final LinearLayout linearLayout = (LinearLayout)  findViewById(R.id.linearLayout);
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
                if(isDataOn()) {
                    Intent mIntent = new Intent(MainActivity.this, QuestionnaireIntroActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putString("interview", "meal");
                    mIntent.putExtras(mBundle);
                    startActivity(mIntent);
                }else {
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Get Internet Connection", Snackbar.LENGTH_LONG)
                    .setAction("Settings", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                        }
                    });
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(Color.WHITE);
                    TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.parseColor("#04A64B"));
                    snackbar.show();
                }
            }
        });
        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDataOn()) {
                    Intent mIntent = new Intent(MainActivity.this, QuestionnaireIntroActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putString("interview", "generalStay");
                    mIntent.putExtras(mBundle);
                    startActivity(mIntent);
                }else {
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Get Internet Connection", Snackbar.LENGTH_LONG)
                            .setAction("Settings", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                                }
                            });
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(Color.WHITE);
                    TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.parseColor("#04A64B"));
                    snackbar.show();
                }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        FetchGeneralStayQuestions();
        FetchMealQuestions();
    }

    public boolean isCurrentUser(){

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        final String authToken = sharedPreferences.getString("authToken", "");

        if(authToken == null || authToken.isEmpty() || authToken.length() < 0){
            return false;
        }

        return true;
    }


    public boolean isDataOn(){
        ConnectivityManager cm = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null
        // otherwise check if phone is connected
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
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
                                GnrlStayQstnsIfRatings.put(obj.getString("id"), Boolean.valueOf(obj.getString("isAboutRating")));
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
                                MealQstnsIfRatings.put(obj.getString("id"), Boolean.valueOf(obj.getString("isAboutRating")));
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
