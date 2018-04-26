package com.example.isellgren.suncatcher;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

    }

    public void Devices(View view) {
        Intent p = new Intent(MainActivity.this, DeviceActivity.class);
        startActivity(p);
    }

    //public void Status(View view) {
      //  Intent k = new Intent(tester.this, Statistic.class);
        //startActivity(k);
    //}
    public void Mail(View view) {
        Intent l = new Intent(MainActivity.this, Mail.class);
        startActivity(l);
    }
}
