package com.example.isellgren.suncatcher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Selectdevice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectdevice);
    }

    public void kitTapButton(View view) {
        Button button = (Button)findViewById(R.id.kitcbut);


        Intent a = new Intent(Selectdevice.this, KitchenActivity.class);
        startActivity(a);

    }

    public void bedTapButton(View view) {
        Button button = (Button)findViewById(R.id.bedbut);


        Intent b = new Intent(Selectdevice.this, BedroomActivity.class);
        startActivity(b);
    }

    public void livTapButton(View view) {
        Button button = (Button)findViewById(R.id.livingbut);


        Intent c = new Intent(Selectdevice.this, Living_RoomActivity.class);
        startActivity(c);
    }
}
