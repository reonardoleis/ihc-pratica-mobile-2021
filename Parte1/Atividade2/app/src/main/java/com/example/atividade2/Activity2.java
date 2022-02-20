package com.example.atividade2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    private TextView message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        message = (TextView) findViewById(R.id.textView);
        Intent intent = getIntent();
        String messageString = intent.getStringExtra("message");
        message.setText(messageString);
    }
}