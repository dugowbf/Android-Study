package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static  final String LOCAL_FILE = "memo_data.txt";

    EditText edit01;

    Button btn01;

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

        edit01 = (EditText) findViewById(R.id.editText01);

        btn01 = (Button) findViewById(R.id.button01);
        btn01.setOnClickListener(this);

        InputStream in;
        BufferedReader reader;

        try {
            in = openFileInput(LOCAL_FILE);
        } catch (FileNotFoundException e) {
            in = getResources().openRawResource(R.raw.raw_data);
        }

        try {
            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String s;
            while ( (s = reader.readLine()) != null) {
                edit01.append(s);
                edit01.append("\n");
            }
        } catch (IOException e) {
            Log.e("Local File", e.getMessage());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        String s = edit01.getText().toString();
        if (s.length() == 0) {
            deleteFile(LOCAL_FILE);
            return;
        }
        try {
            OutputStream out = openFileOutput(LOCAL_FILE, MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.append(s);
            writer.close();
        } catch (IOException e) {
            Log.e("Local File", e.getMessage());
        }
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}