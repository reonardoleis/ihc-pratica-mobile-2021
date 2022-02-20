package com.example.atividade1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private EditText field1;
    private EditText field2;
    private Button sumButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        field1 = (EditText) findViewById(R.id.field1);
        field2 = (EditText) findViewById(R.id.field2);
        sumButton = (Button) findViewById(R.id.button);
        resultText = (TextView) findViewById(R.id.textView);

        sumButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                float a = Float.parseFloat(field1.getText().toString());
                float b = Float.parseFloat(field2.getText().toString());
                float sum = a + b;
                resultText.setText("RESULT: " + String.valueOf(sum));
            }
        });

    }
}