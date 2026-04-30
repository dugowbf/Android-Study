package com.example.myapplication256;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener {

    GridView grid01;
    Button btn01;

    public static Integer[] ImageIds = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4,
            R.drawable.a5,
            R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10,
            R.drawable.a11, R.drawable.a12, R.drawable.a12, R.drawable.a13, R.drawable.a14};


    public static String[] string_text = {"imgae a1", "imgae a2", "imgae a3", "imgae a4", "imgae a5"
            "imgae a6", "imgae a7", "imgae a8", "imgae a9", "imgae a10"};

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

        grid01 = (GridView) findViewById(R.id.grid01);
        grid01.setOnItemClickListener(this);

        grid01.setAdapter(new UserAdapter(this));

        grid01.setNumColumns(3);
        grid01.setColumnWidth(GridView.AUTO_FIT);
        grid01.setVerticalSpacing(10);
        grid01.setHorizontalSpacing(10);
        grid01.setGravity(Gravity.CENTER);
        grid01.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);




    }

    @Override
    public void onClick(View view) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


    }

    private class UserAdapter extends BaseAdapter {

        LayoutInflater inflater;

        private Context mContext;

        public UserAdapter (Context context) {
            mContext = context;

            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return ImageIds.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return ImageIds[i];
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view == null) {
                view = inflater.inflate(R.layout.data_layout, null);
            }

            ImageView data_image = (ImageView) view.findViewById(R.id.data_image);
            TextView data_text01 = (TextView) view.findViewById(R.id.data_text01);
            TextView data_text02 = (TextView) view.findViewById(R.id.data_text02);

            data_image.setImageResource(ImageIds[i]);
            data_text01.setText("데이터설정");
            data_text02.setText(string_text[i]);

            return view;
//            ImageView imageView01;
//            if(view == null) {
//                imageView01 = new ImageView(mContext);
//                imageView01.setLayoutParams(new ViewGroup.LayoutParams(85,85));
//                imageView01.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                imageView01.setPadding(5,5,5,5);
//            } else {
//                imageView01 = (ImageView) view;
//            }
//            imageView01.setImageResource(ImageIds[i]);
//            return imageView01;
        }
    }
}