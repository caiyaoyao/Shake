package com.yaoyao.shake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.yaoyao.sensor.ShakeSensor;

public class MainActivity extends AppCompatActivity implements ShakeSensor.OnShakeListener{
    private ShakeSensor shakeSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shakeSensor = new ShakeSensor(this);
        shakeSensor.setmOnShakeListener(this);
        shakeSensor.init();
    }

    @Override
    public void onShake() {
        Toast.makeText(MainActivity.this, "摇一摇成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,ShowActivity.class));
    }
}
