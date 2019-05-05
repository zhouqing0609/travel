package com.example.kaoshib;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler() {
    };
    private TextView tv;
    private int time = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv = (TextView) findViewById(R.id.tv);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (time>0){
                    time--;
                    handler.postDelayed(this, 1000);
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1);
                    scaleAnimation.setFillAfter(true);
                    scaleAnimation.setDuration(5000);
                    tv.setAnimation(scaleAnimation);
                }else {
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);
                }
            }
        },1000);
    }
}
