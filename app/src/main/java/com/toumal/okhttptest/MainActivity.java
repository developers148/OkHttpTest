package com.toumal.okhttptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    public static String serverresponse;

    static TextView mytext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mytext =findViewById(R.id.textEmail);

        new response("https://reqres.in/api/unknown",MainActivity.this);



    }

    public static void fun(){
        mytext.setText(serverresponse);



    }
}
