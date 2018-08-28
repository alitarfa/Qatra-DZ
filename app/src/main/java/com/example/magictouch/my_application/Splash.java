package com.example.magictouch.my_application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.magictouch.my_application.Authentication.Login;

/**
 * Created by Magic touch on 03/06/2018.
 */


public class Splash extends Activity {
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        textView = (TextView) findViewById(R.id.text_splash);
        imageView = (ImageView) findViewById(R.id.log);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.myanimation);

        textView.startAnimation(animation);
        imageView.startAnimation(animation);

        Intent intent = new Intent(this, Login.class);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();


    }
}
