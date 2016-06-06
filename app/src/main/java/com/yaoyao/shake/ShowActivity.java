package com.yaoyao.shake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        overridePendingTransition(0,R.anim.show_set_out);
    }
}
