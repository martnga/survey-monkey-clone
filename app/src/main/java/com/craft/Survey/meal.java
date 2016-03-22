package com.craft.Survey;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class meal extends AppCompatActivity {
    RelativeLayout breakfast,lunch,dinner;
    TextView meal,DinnerB,lunchB,breakB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        breakfast=(RelativeLayout) findViewById(R.id.breakfast);
        lunch=(RelativeLayout) findViewById(R.id.lunch);
        dinner=(RelativeLayout) findViewById(R.id.dinner);
        meal=(TextView) findViewById(R.id.meal);
        DinnerB=(TextView) findViewById(R.id.DinnerB);
        lunchB=(TextView) findViewById(R.id.lunchB);
        breakB=(TextView) findViewById(R.id.breakB);
        Typeface CustomFont=Typeface.createFromAsset(getAssets(),"fonts/SourceSerifPro-Regular.ttf");
        meal.setTypeface(CustomFont);
        Typeface ButtonFont=Typeface.createFromAsset(getAssets(),"fonts/SourceSerifPro-Bold.ttf");
        DinnerB.setTypeface(ButtonFont);
        lunchB.setTypeface(ButtonFont);
        breakB.setTypeface(ButtonFont);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(meal.this, BreakFast.class);
                intent.putExtra("MealType","Break Fast");
                startActivity(intent);
            }
        });
        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(meal.this,BreakFast.class);
                intent.putExtra("MealType","Lunch");
                startActivity(intent);
            }
        });
        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(meal.this,BreakFast.class);
                intent.putExtra("MealType","Dinner");
                startActivity(intent);
            }
        });
    }
}
