package com.yaoyao.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * Created by Administrator on 2016/6/4.
 */
public class ShakeSensor implements SensorEventListener{
    private static final String TAG = ShakeSensor.class.getSimpleName();
    private Context mContext;
    private SensorManager  manager;
    private Sensor sensor;
    private long lastTime;//记录最后一次的时间

    private float  lastX, lastY, lastZ;

    private static  final  int SPEED_SHRESHOLD = 4800;
    private OnShakeListener mOnShakeListener;

    public ShakeSensor(Context mContext) {
        this.mContext = mContext;
    }

    public ShakeSensor() {
    }

    public void init(){
        manager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_GAME);
    }
    public void unRegisterListener(){
        manager.unregisterListener(this,sensor);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime > 10) {
            long timeDistance = currentTime - lastTime;
            lastTime = currentTime;


            //当前时间x,y,x轴的值
            float x = event.values[0];//x轴变化的值
            float y = event.values[1];//y轴变化的值
            float z = event.values[2];//z轴变化的值
            Log.i(TAG, "onSensorChanged: ========x:" + x + "=========y:" + y + "============z:" + z);

            //速度的阈值
            double  speed;
            double absValue =  Math.abs(x + y + z - lastX - lastY - lastZ);
            speed = absValue / timeDistance * 10000;

            if (speed > SPEED_SHRESHOLD) {
                //当x,y,z达到一定值后进行后续操作
                if (mOnShakeListener != null) {
                    mOnShakeListener.onShake();
                }
            }
            lastX = x;
            lastY = y;
            lastZ = z;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public interface  OnShakeListener{
        void onShake();

    }

    public OnShakeListener getmOnShakeListener() {
        return mOnShakeListener;
    }

    public void setmOnShakeListener(OnShakeListener mOnShakeListener) {
        this.mOnShakeListener = mOnShakeListener;
    }
}
