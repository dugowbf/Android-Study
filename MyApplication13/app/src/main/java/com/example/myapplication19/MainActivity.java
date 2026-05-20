package com.example.myapplication19;

import android.Manifest;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView text01;
    Button btn01, btn02;

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
        btn02.setOnClickListener(this);

        text01 = (TextView) findViewById(R.id.textview01);

        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button01) {
            ContentResolver cr = getContentResolver();
            Cursor c = cr.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null,
                    null, null,MediaStore.Audio.Media.TITLE + " ASC");

            int albumIdx = c.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM);
            int titleIdx = c.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE);
            int artistIdx = c.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST);

            StringBuffer buff = new StringBuffer();

            buff.append("Music List\n\n");

            if (c.moveToFirst()) {
                do {
                    buff.append(c.getString(albumIdx));
                    buff.append("\n");
                    buff.append(c.getString(titleIdx));
                    buff.append("\n");
                    buff.append(c.getString(artistIdx));
                    buff.append("\n");
                    buff.append("----------------------\n");
                } while (c.moveToNext());
            }

            text01.setText(buff);
        }
        if (v.getId() == R.id.button02) {
            finish();
        }

    }
}