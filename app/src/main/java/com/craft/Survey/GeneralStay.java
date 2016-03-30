package com.craft.Survey;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.viewanimator.ViewAnimator;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class GeneralStay extends AppCompatActivity {
    TextView meal1, options, counter, highrate, lowrate;
    ImageView Smiley1, Smiley2, Smiley3, Smiley4, Smiley5;
    LinearLayout layoutval, moredetails;
    LinearLayout LSmilies;
    FloatingActionButton done;
    CoordinatorLayout main_content;
    ImageView backdrop;
    LayerDrawable stars;
    int i = 0, j = 0;
    int TotalQuestions = 0;
    RatingBar ratingBar;
    String[] Questions = {"How enjoyable was your stay at Kapese Village?", "How would you rate the following aspects of your stay?"};
    String[] MealAspects = {"Reception", "Housekeeping", "Dining", "Maintenance", "Laundry", "Catering", "Grounds,Medical"};
    String MealType = "";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_stay);
        meal1 = (TextView) findViewById(R.id.meal1);
        options = (TextView) findViewById(R.id.options);
        highrate = (TextView) findViewById(R.id.highrate);
        lowrate = (TextView) findViewById(R.id.lowrate);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        Smiley1 = (ImageView) findViewById(R.id.Smiley1);
        Smiley2 = (ImageView) findViewById(R.id.Smiley2);
        Smiley3 = (ImageView) findViewById(R.id.Smiley3);
        Smiley4 = (ImageView) findViewById(R.id.Smiley4);
        Smiley5 = (ImageView) findViewById(R.id.Smiley5);
        moredetails = (LinearLayout) findViewById(R.id.moredetails);
        LSmilies = (LinearLayout) findViewById(R.id.LSmilies);
        layoutval = (LinearLayout) findViewById(R.id.layoutval);
        main_content = (CoordinatorLayout) findViewById(R.id.main_content);
        //stars = (LayerDrawable) ratingBar.getProgressDrawable();
        counter = (TextView) findViewById(R.id.counter);
        done = (FloatingActionButton) findViewById(R.id.Submit);
        backdrop = (ImageView) findViewById(R.id.backdrop);
        Typeface CustomFont = Typeface.createFromAsset(getAssets(), "fonts/SourceSerifPro-Regular.ttf");
        meal1.setTypeface(CustomFont);
        Typeface ButtonFont = Typeface.createFromAsset(getAssets(), "fonts/SourceSerifPro-Bold.ttf");
        options.setTypeface(ButtonFont);
        moredetails.setVisibility(View.GONE);
        lowrate.setText("Not enjoyable at all");
        highrate.setText("Very enjoyable");
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        collapsingToolbar.setTitle("General Stay");
        backdrop.setImageDrawable(this.getResources().getDrawable(R.drawable.kapese));
        counter.setText("1/" + String.valueOf(Questions.length + 1));

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //Toast.makeText(Meal.this, "Rates " + ratingBar.getRating(), Toast.LENGTH_SHORT).show();
                if (ratingBar.getRating() == 5.0) {
                    Smiley1.setImageDrawable(GeneralStay.this.getResources().getDrawable(R.drawable.thumbs_up));
                    Smiley2.setImageDrawable(GeneralStay.this.getResources().getDrawable(R.drawable.thumbs_up));
                    Smiley3.setImageDrawable(GeneralStay.this.getResources().getDrawable(R.drawable.thumbs_up));
                    Smiley4.setImageDrawable(GeneralStay.this.getResources().getDrawable(R.drawable.thumbs_up));
                    Smiley5.setImageDrawable(GeneralStay.this.getResources().getDrawable(R.drawable.thumbs_up));
                    Smiley5.setVisibility(View.VISIBLE);
                    Smiley4.setVisibility(View.VISIBLE);
                    Smiley3.setVisibility(View.VISIBLE);
                    Smiley2.setVisibility(View.VISIBLE);
                    Smiley1.setVisibility(View.VISIBLE);
                } else if (ratingBar.getRating() == 4.0) {
                        Smiley1.setImageDrawable(GeneralStay.this.getResources().getDrawable(R.drawable.happy));
                        Smiley2.setImageDrawable(GeneralStay.this.getResources().getDrawable(R.drawable.happy));
                        Smiley3.setImageDrawable(GeneralStay.this.getResources().getDrawable(R.drawable.happy));
                        Smiley4.setImageDrawable(GeneralStay.this.getResources().getDrawable(R.drawable.happy));
                        Smiley5.setVisibility(View.INVISIBLE);
                        Smiley4.setVisibility(View.VISIBLE);
                        Smiley3.setVisibility(View.VISIBLE);
                        Smiley2.setVisibility(View.VISIBLE);
                        Smiley1.setVisibility(View.VISIBLE);
                    } else if (ratingBar.getRating() == 3.0) {
                        Smiley1.setImageDrawable(GeneralStay.this.getResources().getDrawable(R.drawable.happy));
                        Smiley2.setImageDrawable(GeneralStay.this.getResources().getDrawable(R.drawable.happy));
                        Smiley3.setImageDrawable(GeneralStay.this.getResources().getDrawable(R.drawable.happy));
                        Smiley5.setVisibility(View.INVISIBLE);
                        Smiley4.setVisibility(View.INVISIBLE);
                        Smiley3.setVisibility(View.VISIBLE);
                        Smiley2.setVisibility(View.VISIBLE);
                        Smiley1.setVisibility(View.VISIBLE);
                    } else if (ratingBar.getRating() == 2.0) {
                        Smiley1.setImageDrawable(GeneralStay.this.getResources().getDrawable(R.drawable.sad));
                        Smiley2.setImageDrawable(GeneralStay.this.getResources().getDrawable(R.drawable.sad));
                        Smiley3.setVisibility(View.INVISIBLE);
                        Smiley5.setVisibility(View.INVISIBLE);
                        Smiley4.setVisibility(View.INVISIBLE);
                        Smiley2.setVisibility(View.VISIBLE);
                        Smiley1.setVisibility(View.VISIBLE);
                    } else if (ratingBar.getRating() == 1.0) {
                        Smiley1.setImageDrawable(GeneralStay.this.getResources().getDrawable(R.drawable.thumbs_down));
                        Smiley2.setVisibility(View.INVISIBLE);
                        Smiley3.setVisibility(View.INVISIBLE);
                        Smiley5.setVisibility(View.INVISIBLE);
                        Smiley4.setVisibility(View.INVISIBLE);
                        Smiley1.setVisibility(View.VISIBLE);
                    }

                ViewAnimator
                        .animate(LSmilies)
                        .translationY(1000, 0)
                        .alpha(0, 1)
                        .descelerate()
                        .duration(500)

                        .thenAnimate(LSmilies)
                        .scale(1f, 0.5f, 1f)
                        .accelerate()
                        .duration(1000)

                        .start();
                //stars.getDrawable(1).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
            }
        });


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ratingBar.getRating() > 0.0) {
                    if (i < Questions.length - 1 || j < MealAspects.length - 1) {
                        if (i < Questions.length - 1) {
                            i = i + 1;
                            counter.setText(String.valueOf(i + 1) + "/" + String.valueOf(Questions.length + 1));
                        }
                        if (i == Questions.length - 1) {
                            lowrate.setText("Poor");
                            highrate.setText("Excellent");
                            options.setText(MealAspects[j]);
                            j = j + 1;
                            ViewAnimator
                                    .animate(options)
                                    .translationX(1000, 0)
                                    .alpha(0, 1)
                                    .descelerate()
                                    .duration(1000)

                                    .thenAnimate(LSmilies)
                                    .scale(1f, 0.5f, 1f)
                                    .accelerate()
                                    .duration(1000)

                                    .start();
                        }
                        meal1.setText(Questions[i].replace("Meals", MealType));
                        ratingBar.setRating(0.0f);
                        if (i != Questions.length - 1) {
                            ViewAnimator
                                    .animate(meal1)
                                    .translationX(1000, 0)
                                    .alpha(0, 1)
                                    .descelerate()
                                    .duration(500)

                                    .thenAnimate(LSmilies)
                                    .scale(1f, 0.5f, 1f)
                                    .accelerate()
                                    .duration(1000)

                                    .start();
                        }
                        //Toast.makeText(Meal.this, "Rates " + ratingBar.getRating(),Toast.LENGTH_SHORT).show();
                    }
                    if (MealType.equals("Stay")) {
                        Intent intent = new Intent(GeneralStay.this, FinalScreen.class);
                        startActivity(intent);
                    }
                    if (j == MealAspects.length - 1) {
                        layoutval.setVisibility(View.GONE);
                        ratingBar.setVisibility(View.GONE);
                        meal1.setVisibility(View.GONE);
                        options.setVisibility(View.GONE);
                        counter.setText(String.valueOf(i + 2) + "/" + String.valueOf(Questions.length + 1));
                        moredetails.setVisibility(View.VISIBLE);
                        MealType = "Stay";
                    }
                    Smiley5.setVisibility(View.INVISIBLE);
                    Smiley4.setVisibility(View.INVISIBLE);
                    Smiley3.setVisibility(View.INVISIBLE);
                    Smiley2.setVisibility(View.INVISIBLE);
                    Smiley1.setVisibility(View.INVISIBLE);

                } else if (j != MealAspects.length - 1) {
                    Snackbar snackbar = Snackbar
                            .make(main_content, "Please Provide Your FeedBack.", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(Color.parseColor("#005FAA"));
                    TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);
                    snackbar.show();
                }
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "GeneralStay Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.craft.Survey/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "GeneralStay Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.craft.Survey/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
