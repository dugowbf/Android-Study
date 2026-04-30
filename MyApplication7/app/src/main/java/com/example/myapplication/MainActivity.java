package com.example.myapplication;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener, View.OnFocusChangeListener, View.OnKeyListener, View.OnTouchListener{


    Button btn01, btn02;

    EditText edit01, edit02;

    TextView text01;

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

        btn01 = (Button) findViewById(R.id.button01);
        btn01.setOnClickListener(this);

        btn02 = (Button) findViewById(R.id.button02);
        btn02.setOnLongClickListener(this);

        edit01 = (EditText) findViewById(R.id.editText01);
        edit01.setOnFocusChangeListener(this);

        edit02 = (EditText) findViewById(R.id.editText02);
        edit02.setOnTouchListener(this);

        text01 = (TextView) findViewById(R.id.textView01);
        text01.setOnTouchListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "onClick", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onLongClick(View v) {
        Toast.makeText(this, "onLongClick", Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        String s = "";
        if (hasFocus) {
            ((EditText) v).setText("");
        } else {
            s = edit01.getText().toString();
            edit01.setText(s + " + focus change");
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            text01.setText("Volume Up");
        }
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            text01.setText("Volume Down");
        }
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            text01.setText("ACTION_DOWN");
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            text01.setText("ACTION_MOVE");
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            text01.setText("ACTION_UP");
            return true;
        }
        return false;
    }
}