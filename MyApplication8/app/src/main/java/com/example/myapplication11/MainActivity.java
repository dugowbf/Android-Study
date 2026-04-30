package com.example.myapplication11;

import static android.app.ProgressDialog.show;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    int tbColor;

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

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tbColor = ContextCompat.getColor(this, R.color.tb_color);

        toolbar.setBackgroundColor(tbColor);
        getWindow().setStatusBarColor(tbColor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Toast.makeText(this, "Home", Toast.LENGTH_LONG).show();
        }
        if (id == R.id.item01) {
            Toast.makeText(this,"Menu 1", Toast.LENGTH_LONG).show();
        }
        if (id == R.id.item02) {
            Toast.makeText(this, "Menu 2", Toast.LENGTH_LONG).show();
        }
        if (id == R.id.item03) {
            Toast.makeText(this, "Sub Menu", Toast.LENGTH_LONG).show();
        }
        if (id == R.id.item04) {
            Toast.makeText(this, "Sub Menu 1", Toast.LENGTH_LONG).show();
        }
        if (id == R.id.item05) {
            Toast.makeText(this, "Sub Menu 2 - Close", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}






























