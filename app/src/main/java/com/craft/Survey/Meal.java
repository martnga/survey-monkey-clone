package com.craft.Survey;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.LayerDrawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.github.florent37.viewanimator.ViewAnimator;

public class Meal extends AppCompatActivity {
    TextView meal1,options,counter;
    ImageView Smiley1,Smiley2,Smiley3,Smiley4,Smiley5;
    LinearLayout LSmilies;
    FloatingActionButton done;
    CoordinatorLayout main_content;
    ImageView backdrop;
    LayerDrawable stars;
    int i=0,j=0;
    int TotalQuestions=0;
    RatingBar ratingBar;
    String [] Questions={"How did you enjoy your Meals?","How would you rate the following aspects of your Meals?"};
    String [] MealAspects={"Temperature","Presentation","Portion Size","Variety","Service","Cleanliness of facility","Staff Courtesy"};
    String MealType="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        meal1=(TextView) findViewById(R.id.meal1);
        options=(TextView) findViewById(R.id.options);
        ratingBar=(RatingBar) findViewById(R.id.ratingBar);
        Smiley1=(ImageView) findViewById(R.id.Smiley1);
        Smiley2=(ImageView) findViewById(R.id.Smiley2);
        Smiley3=(ImageView) findViewById(R.id.Smiley3);
        Smiley4=(ImageView) findViewById(R.id.Smiley4);
        Smiley5=(ImageView) findViewById(R.id.Smiley5);
        LSmilies=(LinearLayout) findViewById(R.id.LSmilies);
        main_content=(CoordinatorLayout) findViewById(R.id.main_content);
        //stars = (LayerDrawable) ratingBar.getProgressDrawable();
        counter=(TextView) findViewById(R.id.counter);
        done=(FloatingActionButton) findViewById(R.id.Submit);
        backdrop=(ImageView) findViewById(R.id.backdrop);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        Bundle bundle = getIntent().getExtras();
        MealType=bundle.getString("MealType");
        meal1.setText(Questions[i].replace("Meals",MealType));
        Typeface CustomFont=Typeface.createFromAsset(getAssets(),"fonts/SourceSerifPro-Regular.ttf");
        meal1.setTypeface(CustomFont);
        Typeface ButtonFont=Typeface.createFromAsset(getAssets(),"fonts/SourceSerifPro-Bold.ttf");
        options.setTypeface(ButtonFont);
        if(MealType.equals("Break Fast")){
            collapsingToolbar.setTitle("Break Fast");
            backdrop.setImageDrawable(this.getResources().getDrawable(R.drawable.breakfast));
        }
        else if(MealType.equals("Lunch")){
            collapsingToolbar.setTitle("Lunch");
            backdrop.setImageDrawable(this.getResources().getDrawable(R.drawable.lunch));
        }
        else{
            collapsingToolbar.setTitle("Dinner");
            backdrop.setImageDrawable(this.getResources().getDrawable(R.drawable.dinner));
        }
        counter.setText("1/"+String.valueOf(Questions.length));

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //Toast.makeText(Meal.this, "Rates " + ratingBar.getRating(), Toast.LENGTH_SHORT).show();
                if(ratingBar.getRating()==5.0){
                    Smiley1.setImageDrawable(Meal.this.getResources().getDrawable(R.drawable.thumbs_up));
                    Smiley2.setImageDrawable(Meal.this.getResources().getDrawable(R.drawable.thumbs_up));
                    Smiley3.setImageDrawable(Meal.this.getResources().getDrawable(R.drawable.thumbs_up));
                    Smiley4.setImageDrawable(Meal.this.getResources().getDrawable(R.drawable.thumbs_up));
                    Smiley5.setImageDrawable(Meal.this.getResources().getDrawable(R.drawable.thumbs_up));
                    Smiley5.setVisibility(View.VISIBLE);
                    Smiley4.setVisibility(View.VISIBLE);
                    Smiley3.setVisibility(View.VISIBLE);
                    Smiley2.setVisibility(View.VISIBLE);
                    Smiley1.setVisibility(View.VISIBLE);
                }
                else if(ratingBar.getRating()==4.0){
                    Smiley1.setImageDrawable(Meal.this.getResources().getDrawable(R.drawable.happy));
                    Smiley2.setImageDrawable(Meal.this.getResources().getDrawable(R.drawable.happy));
                    Smiley3.setImageDrawable(Meal.this.getResources().getDrawable(R.drawable.happy));
                    Smiley4.setImageDrawable(Meal.this.getResources().getDrawable(R.drawable.happy));
                    Smiley5.setVisibility(View.INVISIBLE);
                    Smiley4.setVisibility(View.VISIBLE);
                    Smiley3.setVisibility(View.VISIBLE);
                    Smiley2.setVisibility(View.VISIBLE);
                    Smiley1.setVisibility(View.VISIBLE);
                }
                else if(ratingBar.getRating()==3.0){
                    Smiley1.setImageDrawable(Meal.this.getResources().getDrawable(R.drawable.happy));
                    Smiley2.setImageDrawable(Meal.this.getResources().getDrawable(R.drawable.happy));
                    Smiley3.setImageDrawable(Meal.this.getResources().getDrawable(R.drawable.happy));
                    Smiley5.setVisibility(View.INVISIBLE);
                    Smiley4.setVisibility(View.INVISIBLE);
                    Smiley3.setVisibility(View.VISIBLE);
                    Smiley2.setVisibility(View.VISIBLE);
                    Smiley1.setVisibility(View.VISIBLE);
                }
                else if(ratingBar.getRating()==2.0){
                    Smiley1.setImageDrawable(Meal.this.getResources().getDrawable(R.drawable.sad));
                    Smiley2.setImageDrawable(Meal.this.getResources().getDrawable(R.drawable.sad));
                    Smiley3.setVisibility(View.INVISIBLE);
                    Smiley5.setVisibility(View.INVISIBLE);
                    Smiley4.setVisibility(View.INVISIBLE);
                    Smiley2.setVisibility(View.VISIBLE);
                    Smiley1.setVisibility(View.VISIBLE);
                }
                else if(ratingBar.getRating()==1.0){
                    Smiley1.setImageDrawable(Meal.this.getResources().getDrawable(R.drawable.thumbs_down));
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
                    if(j==MealAspects.length-1)
                    {
                        Intent intent=new Intent(Meal.this,FinalScreen.class);
                        startActivity(intent);
                    }
                    if (i < Questions.length - 1 || j < MealAspects.length - 1) {
                        if (i < Questions.length - 1) {
                            i = i + 1;
                            counter.setText(String.valueOf(i + 1) + "/" + String.valueOf(Questions.length));
                        }
                        if (i == Questions.length - 1) {
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
                    Smiley5.setVisibility(View.INVISIBLE);
                    Smiley4.setVisibility(View.INVISIBLE);
                    Smiley3.setVisibility(View.INVISIBLE);
                    Smiley2.setVisibility(View.INVISIBLE);
                    Smiley1.setVisibility(View.INVISIBLE);
                }
                else{
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
    }
}
