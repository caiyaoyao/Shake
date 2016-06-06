package com.yaoyao.shake;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.yaoyao.sensor.ShakeSensor;

public class MainActivity extends AppCompatActivity implements ShakeSensor.OnShakeListener{
    private ShakeSensor shakeSensor;
    private MediaPlayer mPlayer;
    private Vibrator mVibrator;
    private ImageView mImgHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImgHandler = (ImageView) findViewById(R.id.img_show);
        shakeSensor = new ShakeSensor(this);
        shakeSensor.setmOnShakeListener(this);
        shakeSensor.init();
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.main_img_handler);
        mImgHandler.setAnimation(animation);
    }

    @Override
    public void onShake() {
//        Toast  toast  = Toast.makeText(MainActivity.this, "摇一摇成功", Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.CENTER,0,0);
//        toast.show();
        //震动，音乐效果
        startAudioWithVibrator();
        startActivity(new Intent(this,ShowActivity.class));
        //添加跳转时ShowActivity进入的动画
        overridePendingTransition(R.anim.main_set_in,0);
    }

    private void startAudioWithVibrator() {
//        mPlayer = MediaPlayer.create(this,R.raw.hehe);
//        mPlayer.start();
        long pattern[] = {500,300,500,300};
        mVibrator.vibrate(pattern,-1);

    }
}
