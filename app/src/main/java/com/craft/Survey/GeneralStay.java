package com.craft.Survey;




import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;



public class GeneralStay extends AppCompatActivity {
   /* Snackbar snackbar = Snackbar
            .make(main_content, "Please Provide Your FeedBack.", Snackbar.LENGTH_LONG);
    View snackbarView = snackbar.getView();
    snackbarView.setBackgroundColor(Color.parseColor("#005FAA"));
    TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
    textView.setTextColor(Color.WHITE);
    snackbar.show();*/

    FloatingActionButton mNextFab;
    CoordinatorLayout main_content;
    String[] Questions = {"How enjoyable was your stay at Kapese Village?", "How would you rate the following aspects of your stay?"};
    String[] MealAspects = {"Reception", "Housekeeping", "Dining", "Maintenance", "Laundry", "Catering", "Grounds,Medical"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_stay);

        main_content = (CoordinatorLayout) findViewById(R.id.main_content);
        mNextFab = (FloatingActionButton) findViewById(R.id.next_fab);

    }
    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
