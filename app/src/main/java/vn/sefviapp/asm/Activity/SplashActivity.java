package vn.sefviapp.asm.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import vn.sefviapp.asm.R;
import vn.sefviapp.asm.Utils.Utils;

public class SplashActivity extends AppCompatActivity {
    static int TimeOut_Millis = 2000;
    ImageView imgLogofpt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imgLogofpt = findViewById(R.id.splashImage);

        Utils.darkenStatusBar(this, R.color.colorPrimary);

        Animation animFade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        imgLogofpt.startAnimation(animFade);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },TimeOut_Millis);

    }
}
