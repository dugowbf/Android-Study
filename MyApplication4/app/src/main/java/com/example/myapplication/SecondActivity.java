package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText01;

    Button btn01;

    String receive, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText01 = (EditText) findViewById(R.id.result_editText);

        btn01 = (Button) findViewById(R.id.button03);
        btn01.setOnClickListener(this);

        receive = getIntent().getStringExtra("SendData");
        editText01.setText(receive);
    }

    @Override
    public void onClick(View v) {
        result = editText01.getText().toString();
        if(!result.isEmpty()) {
            Intent intent01 = new Intent();
            intent01.putExtra("ResultData", result);
            setResult(RESULT_OK, intent01);
        } else {
            setResult(RESULT_CANCELED);
        }
        finish();

    }
}