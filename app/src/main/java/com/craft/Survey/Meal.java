package com.craft.Survey;



import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.github.florent37.viewanimator.ViewAnimator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.grabner.circleprogress.AnimationState;
import at.grabner.circleprogress.AnimationStateChangedListener;
import at.grabner.circleprogress.CircleProgressView;
import at.grabner.circleprogress.TextMode;
import at.grabner.circleprogress.UnitPosition;


public class Meal extends AppCompatActivity {

    // The log tag.
    private final static String TAG = "MealClass";
    ImageView mRatingImage;

    CircleProgressView mCircleView;
    Switch mSwitchSpin;
    Switch mSwitchShowUnit;
    SeekBar mSeekBar;
    SeekBar mSeekBarSpinnerLength;
    Boolean mShowUnit = true;
    Spinner mSpinner;
    TextView mQuestionTxt;
    public HashMap<String, String> MealAnswers = new HashMap<>();
    int i = 0;

   /* Snackbar snackbar = Snackbar
            .make(main_content, "Please Provide Your FeedBack.", Snackbar.LENGTH_LONG);
    View snackbarView = snackbar.getView();
    snackbarView.setBackgroundColor(Color.parseColor("#005FAA"));
    TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
    textView.setTextColor(Color.WHITE);
    snackbar.show();*/

    FloatingActionButton mNextFab;
    CoordinatorLayout main_content;
    ArrayList<String> keys =  new ArrayList<>();
    float ratingsValue = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        //Setting Title For page
        CollapsingToolbarLayout mCollapseToolBar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        mCollapseToolBar.setTitle("Meals");

        main_content = (CoordinatorLayout) findViewById(R.id.main_content);
        mNextFab = (FloatingActionButton) findViewById(R.id.next_fab);
        mQuestionTxt = (TextView) findViewById(R.id.question_txt);
        mCircleView = (CircleProgressView) findViewById(R.id.circleView);
        mRatingImage = (ImageView) findViewById(R.id.rating_image);

        //Getting The Question IDs
        for (Map.Entry<String, String> entry : MainActivity.MealQuestions.entrySet())
        {

            keys.add(entry.getKey());

        }

        mQuestionTxt.setText(MainActivity.MealQuestions.get(keys.get(i)));

        mCircleView.setOnProgressChangedListener(new CircleProgressView.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(float value) {
                Log.d(TAG, "Progress Changed: " + value);
                int val = (int) value;
                if(val > 50){
                    mRatingImage.setImageDrawable(Meal.this.getResources().getDrawable(R.drawable.thumbs_up));
                }else if (val < 50){
                    mRatingImage.setImageDrawable(Meal.this.getResources().getDrawable(R.drawable.thumbs_down));
                }else if (val == 50){
                    mRatingImage.setImageDrawable(Meal.this.getResources().getDrawable(R.drawable.level));
                }

                ViewAnimator
                        .animate(mRatingImage)
                        .translationY(1000, 0)
                        .alpha(0, 1)
                        .descelerate()
                        .duration(500)

                        .thenAnimate(mRatingImage)
                        .scale(1f, 0.5f, 1f)
                        .accelerate()
                        .duration(1000)

                        .start();
                ratingsValue = value;
            }
        });
        mNextFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ratingsValue == 50) {
                    Snackbar snackbar = Snackbar
                            .make(main_content, "Please Provide Your FeedBack.", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(Color.parseColor("#025b29"));
                    TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);
                    snackbar.show();
                } else {
                    MealAnswers.put(keys.get(i), ratingsValue + "");
                    i++;
                    mQuestionTxt.setText(MainActivity.MealQuestions.get(keys.get(i)));
                    mCircleView.setValue(50);



                }
                if (i >= MainActivity.MealQuestions.size() - 1) {
                    startActivity(new Intent(Meal.this, FinalScreen.class));
                    Log.d(TAG, MealAnswers.toString());
                    finish();
                }
                }
        });

        //value setting
//        mCircleView.setMaxValue(100);
//        mCircleView.setValue(0);
//        mCircleView.setValueAnimated(24);

//        //show unit
//        mCircleView.setUnit("%");
//        mCircleView.setUnitVisible(mShowUnit);
//
//        //text sizes
//        mCircleView.setTextSize(50); // text size set, auto text size off
//        mCircleView.setUnitSize(40); // if i set the text size i also have to set the unit size
//        mCircleView.setAutoTextSize(true); // enable auto text size, previous values are overwritten
//        //if you want the calculated text sizes to be bigger/smaller you can do so via
//        mCircleView.setUnitScale(0.9f);
//        mCircleView.setTextScale(0.9f);
//
////        //custom typeface
////        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/ANDROID_ROBOT.ttf");
////        mCircleView.setTextTypeface(font);
////        mCircleView.setUnitTextTypeface(font);
//
//
//        //color
//        //you can use a gradient
        mCircleView.setBarColor(getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorOne),
                getResources().getColor(R.color.colorTwo),
                getResources().getColor(R.color.colorThree));
//
//        //colors of text and unit can be set via
//        mCircleView.setTextColor(Color.RED);
//        mCircleView.setTextColor(Color.BLUE);
//        //or to use the same color as in the gradient
//        mCircleView.setTextColorAuto(true); //previous set values are ignored
//
//        //text mode
//        mCircleView.setText("Text"); //shows the given text in the circle view
//        mCircleView.setTextMode(TextMode.TEXT); // Set text mode to text to show text
//
//        //in the following text modes, the text is ignored
//        mCircleView.setTextMode(TextMode.VALUE); // Shows the current value
//        mCircleView.setTextMode(TextMode.PERCENT); // Shows current percent of the current value from the max value

        //spinning
//        mCircleView.spin(); // start spinning
//        mCircleView.stopSpinning(); // stops spinning. Spinner gets shorter until it disappears.
//        mCircleView.setValueAnimated(24); // stops spinning. Spinner spins until on top. Then fills to set value.


        //animation callbacks

        //this example shows how to show a loading text if it is in spinning mode, and the current percent value otherwise.
        mCircleView.setShowTextWhileSpinning(true); // Show/hide text in spinning mode
        mCircleView.setText("Loading...");
        mCircleView.setOnAnimationStateChangedListener(
                new AnimationStateChangedListener() {
                    @Override
                    public void onAnimationStateChanged(AnimationState _animationState) {
                        switch (_animationState) {
                            case IDLE:
                            case ANIMATING:
                            case START_ANIMATING_AFTER_SPINNING:
                                mCircleView.setTextMode(TextMode.PERCENT); // show percent if not spinning
                                mCircleView.setUnitVisible(mShowUnit);
                                break;
                            case SPINNING:
                                mCircleView.setTextMode(TextMode.TEXT); // show text while spinning
                                mCircleView.setUnitVisible(false);
                            case END_SPINNING:
                                break;
                            case END_SPINNING_START_ANIMATING:
                                break;

                        }
                    }
                }
        );




        // region setup other ui elements
        //Setup Switch
        mSwitchSpin = (Switch) findViewById(R.id.switch1);
        mSwitchSpin.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            mCircleView.spin();
                        } else {
                            mCircleView.stopSpinning();
                        }
                    }
                }

        );

        mSwitchShowUnit = (Switch) findViewById(R.id.switch2);
        mSwitchShowUnit.setChecked(mShowUnit);
        mSwitchShowUnit.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        mCircleView.setUnitVisible(isChecked);
                        mShowUnit = isChecked;
                    }
                }

        );

        //Setup SeekBar
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);

        mSeekBar.setMax(100);
        mSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        mCircleView.setValueAnimated(seekBar.getProgress(), 1500);
                        mSwitchSpin.setChecked(false);
                    }
                }
        );

        mSeekBarSpinnerLength = (SeekBar) findViewById(R.id.seekBar2);
        mSeekBarSpinnerLength.setMax(360);
        mSeekBarSpinnerLength.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        mCircleView.setSpinningBarLength(seekBar.getProgress());
                    }
                });

        mSpinner = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("Left Top");
        list.add("Left Bottom");
        list.add("Right Top");
        list.add("Right Bottom");
        list.add("Top");
        list.add("Bottom");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        mSpinner.setAdapter(dataAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mCircleView.setUnitPosition(UnitPosition.LEFT_TOP);
                        break;
                    case 1:
                        mCircleView.setUnitPosition(UnitPosition.LEFT_BOTTOM);
                        break;
                    case 2:
                        mCircleView.setUnitPosition(UnitPosition.RIGHT_TOP);
                        break;
                    case 3:
                        mCircleView.setUnitPosition(UnitPosition.RIGHT_BOTTOM);
                        break;
                    case 4:
                        mCircleView.setUnitPosition(UnitPosition.TOP);
                        break;
                    case 5:
                        mCircleView.setUnitPosition(UnitPosition.BOTTOM);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpinner.setSelection(2);
        //endregion

//        new LongOperation().execute();

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    private class LongOperation extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            Meal.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mCircleView.setValue(0);
                    mCircleView.spin();
                }
            });

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mCircleView.setValueAnimated(42);
        }
    }


    @Override
    public void onStop() {
        super.onStop();

    }
    }

