package com.craft.Survey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.github.florent37.viewanimator.ViewAnimator;

public class FinalScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_screen);
        ImageView mThankYouImg = (ImageView)findViewById(R.id.thank_you_image);
        ViewAnimator
                .animate(mThankYouImg)
                .translationY(1000, 0)
                .alpha(0, 1)
                .descelerate()
                .duration(500)

                .thenAnimate(mThankYouImg)
                .scale(1f, 0.5f, 1f)
                .accelerate()
                .duration(1000)

                .start();
    }
}
