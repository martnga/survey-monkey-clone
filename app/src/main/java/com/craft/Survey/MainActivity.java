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

import com.github.florent37.viewanimator.ViewAnimator;

import java.util.ArrayList;
import java.util.List;

import at.grabner.circleprogress.AnimationState;
import at.grabner.circleprogress.AnimationStateChangedListener;
import at.grabner.circleprogress.CircleProgressView;
import at.grabner.circleprogress.TextMode;
import at.grabner.circleprogress.UnitPosition;

public class MainActivity extends AppCompatActivity {

    TextView mealbtn,general,Intro;
    public static final String PREFS_NAME = "CREDENTIALS";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
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
        mealbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Meals.class);
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
}
