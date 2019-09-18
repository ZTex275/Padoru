package com.all.soreyo;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

import com.all.soreyo.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    private SoundPool soundPool = null;
    private int sound1, sound2, sound3, sound4, sound5;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-7138654702020421~2476462670");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        }

        sound1 = soundPool.load(this, R.raw.sound1, 1);
        sound2 = soundPool.load(this, R.raw.sound2, 1);
        sound3 = soundPool.load(this, R.raw.sound3, 1);
        sound4 = soundPool.load(this, R.raw.sound4, 1);
        sound5 = soundPool.load(this, R.raw.sound5, 1);
    }


    public void playSound(View v) {
        switch (v.getId()) {
            case R.id.button_sound1:
                soundPool.play(sound1, 1, 1, 0, 0, 1);
                //soundPool.pause(sound3StreamId);
                //soundPool.autoPause();
                break;
            case R.id.button_sound2:
                soundPool.play(sound2, 1, 1, 0, 0, 1);
                break;
            case R.id.button_sound3:
                soundPool.play(sound3, 1, 1, 0, 0, 1);
                break;
            case R.id.button_sound4:
                soundPool.play(sound4, 1, 1, 0, 0, 1);
                break;
            case R.id.button_sound5:
                //загружаем гифку
                WebView wView = new WebView(MainActivity.this);
                wView.loadUrl("file:///android_asset/snow.gif");
                setContentView(wView);

                //идет звук
                soundPool.play(sound5, 1, 1, 0, 0, 1);

                //ставим задержку 2,5сек после чего возвращаемся на главный экран
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        setContentView(R.layout.activity_main);

                        mAdView = findViewById(R.id.adView);
                        AdRequest adRequest = new AdRequest.Builder().build();
                        mAdView.loadAd(adRequest);
                    }
                }, 2500);

                break;
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }

}
