package com.craft.Survey;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

/**
 * Created by mansa on 4/6/16.
 */
public class QuestionnaireIntroActivity extends Activity {
    TextView mStartBtn, mWelcomeTxt;
    RelativeLayout mLayout;

    public static String TAG = "QuestionnaireIntroActivity";

    public static final String PREFS_NAME = "CREDENTIALS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_intro);

        mStartBtn = (TextView) findViewById(R.id.start_questionnaire_btn);
        mWelcomeTxt = (TextView) findViewById(R.id.welcome_txt);
        mLayout = (RelativeLayout) findViewById(R.id.questionnaire_intro_layout);
        final String mQuestionnaireType = getIntent().getExtras().getString("interview");

        if (mQuestionnaireType.equals("meal")){
            mWelcomeTxt.setText("How did you like the meals?");
            mLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.meal));

        }else if(mQuestionnaireType.equals("generalStay")){
            mWelcomeTxt.setText("How did you like your stay?");
            mLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.general_stay));
        }


        mStartBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mQuestionnaireType.equals("meal") && ! MainActivity.MealQuestions.isEmpty()) {
                    startActivity(new Intent(QuestionnaireIntroActivity.this, Meal.class));
                }else if(mQuestionnaireType.equals("generalStay") && !MainActivity.GeneralStayQuestions.isEmpty()) {
                    startActivity(new Intent(QuestionnaireIntroActivity.this, GeneralStay.class));
                }
            }
        });
    }

}
