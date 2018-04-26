package com.example.isellgren.suncatcher;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

public class Schema extends AppCompatActivity implements
        View.OnClickListener {
        private static SeekBar seek_bar;
        private static TextView text_view,infos;
        EditText up, down;
        String Up, Down;
        Context ctx=this;

        Button btnDatePicker, btnTimePicker,btnStopTimePicker;
        EditText txtDate, txtTime, txtStop;
        private int mYear, mMonth, mDay, mHour, mMinute, mEndHour, mEndMinute;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_schema);

                btnDatePicker=(Button)findViewById(R.id.btn_date);
                btnTimePicker=(Button)findViewById(R.id.btn_time);
                btnStopTimePicker=(Button)findViewById(R.id.btn_stop);
                txtDate=(EditText)findViewById(R.id.in_date);
                txtTime=(EditText)findViewById(R.id.in_time);
                txtStop=(EditText)findViewById(R.id.in_stop);


                btnDatePicker.setOnClickListener(this);
                btnTimePicker.setOnClickListener(this);
                btnStopTimePicker.setOnClickListener(this);
                seebbar();

        }

        @Override
        public void onClick(View v) {

                if (v == btnDatePicker) {

                        // Get Current Date
                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);


                        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                                new DatePickerDialog.OnDateSetListener() {

                                        @Override
                                        public void onDateSet(DatePicker view, int year,
                                                              int monthOfYear, int dayOfMonth) {

                                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                        }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();
                }
                if (v == btnTimePicker) {

                        // Get Current Time
                        final Calendar c = Calendar.getInstance();
                        mHour = c.get(Calendar.HOUR_OF_DAY);
                        mMinute = c.get(Calendar.MINUTE);

                        // Launch Time Picker Dialog
                        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                                new TimePickerDialog.OnTimeSetListener() {

                                        @Override
                                        public void onTimeSet(TimePicker view, int hourOfDay,
                                                              int minute) {

                                                txtTime.setText(hourOfDay + ":" + minute);
                                        }
                                }, mHour, mMinute, true);
                        timePickerDialog.show();
                }
            if (v == btnStopTimePicker) {

                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mEndHour = c.get(Calendar.HOUR_OF_DAY);
                mEndMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timeStopPickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                txtStop.setText(hourOfDay + ":" + minute);
                            }
                        }, mEndHour, mEndMinute, true);
                timeStopPickerDialog.show();
            }
        }

    public void seebbar( ){
        seek_bar = (SeekBar)findViewById(R.id.seekBar);
        text_view =(TextView)findViewById(R.id.textView);
        infos = (TextView)findViewById(R.id.info);
        text_view.setText("Covered : " + seek_bar.getProgress());


        seek_bar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        text_view.setText("Covered : " + progress );
                        if(text_view != null) {
                            infos.setText(txtTime.getText().toString() + " - " + txtStop.getText().toString() + System.getProperty("line.separator") + "On date " + txtDate.getText().toString()+ System.getProperty("line.separator") + "Window covered by " + progress+"%");
                        }

                        //Toast.makeText(Schema.this,"SeekBar in progress", Toast.LENGTH_LONG).show();
                        BackGround b = new BackGround();

                        b.execute(txtStop.getText().toString(), "0");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        //Toast.makeText(LoginActivity.this,"SeekBar in StartTracking",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        //text_view.setText("Covered : " + progress_value + " / " +seek_bar.getMax());
                        //Toast.makeText(LoginActivity.this,"SeekBar in StopTracking",Toast.LENGTH_LONG).show();
                    }
                }
        );

    }
    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String up = params[0];
            String down = params[1];
            String id = "1";
            String data="";
            int tmp;

            try {
                URL url = new URL("http://192.168.43.145/process.php");
                String urlParams = "up="+up+"&down="+down+"&id="+id;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }
                is.close();
                httpURLConnection.disconnect();

                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("")){
                s="Data saved successfully.";
            }
            //Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
        }
    }


}
