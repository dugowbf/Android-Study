package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityResultLauncher<Intent> resultLauncher;

    EditText input_edit;

    Button btn01, btn02;

    TextView result_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        input_edit = (EditText) findViewById(R.id.editText01);

        btn01 = (Button) findViewById(R.id.button01);
        btn01.setOnClickListener(this);

        btn02 = (Button) findViewById(R.id.button02);
        btn02.setOnClickListener(this);

        result_text = (TextView) findViewById(R.id.textView02);

        resultLauncher = registerForActivityResult
                (new ActivityResultContracts.StartActivityForResult(),
                        new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult o) {
                                if(o.getResultCode() == RESULT_OK) {
                                    Intent data_intent = o.getData();
                                    String data_result = data_intent.getExtras().getString("ResultData");
                                    result_text.setText(data_result);
                                }
                                if(o.getResultCode() != RESULT_OK) {
                                    result_text.setText("No Data");
                                }
                            }
                });







    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button01) {
            String s = input_edit.getText().toString();
            Intent intent01 = new Intent(MainActivity.this, SecondActivity.class);
            intent01.putExtra("SendData", s);
            resultLauncher.launch(intent01);
        }
        if(v.getId() == R.id.button02) {
            finish();
        }

    }
}















