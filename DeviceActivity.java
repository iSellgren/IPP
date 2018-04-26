package com.example.isellgren.suncatcher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

public class DeviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
    }
    public void schTapButton(View view) {
        Button button = (Button)findViewById(R.id.schmbut);


        Intent q = new Intent(DeviceActivity.this, Schema.class);
        startActivity(q);

    }
    public void didTapButton(View view) {
        Button button = (Button)findViewById(R.id.conbut);


        Intent q = new Intent(DeviceActivity.this, Selectdevice.class);
        startActivity(q);

    }

}
