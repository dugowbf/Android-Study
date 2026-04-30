package com.example.myapplication17;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Grid;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class MainActivity extends AppCompatActivity {

    Button btn01;

    RecyclerView recyclerView01;
    RecyclerView.Adapter adapter;

    private final String[] items = {"itme01", "itme02", "itme03", "itme04", "itme05",
            "itme06", "itme07", "itme08", "itme09", "itme10",
            "itme11", "itme12", "itme13", "itme14", "itme15"};

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
        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView01 = (RecyclerView) findViewById(R.id.recycler01);

        //recyclerView01.setLayoutManager(new GridLayoutManager(this, 3));

        //recyclerView01.setLayoutManager(new LinearLayoutManager(this));

        recyclerView01.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayoutManager.HORIZONTAL));

        adapter = new UserAdapter(items);

        recyclerView01.setAdapter(adapter);
    }

    private class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

        String[] data_items;

        public UserAdapter(String[] items) {
            data_items = items;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            // resource안 Layout에 있는  Souce Code로 가져와야 함
              /*View holderView = LayoutInflater.from(parent.getContext())
                      .inflate(R.layout.data_layout, parent, false);*/

            Context context = parent.getContext();
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View holderView = inflater.inflate(R.layout.data_layout, parent, false);

            MyViewHolder myViewHolder = new MyViewHolder(holderView);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.data_text.setText(data_items[position]);
        }

        @Override
        public int getItemCount() {
            return data_items.length;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView data_text;
            TextView data_text02;
            Button data_btn;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                data_text = itemView.findViewById(R.id.data_textView);
                data_btn = itemView.findViewById(R.id.data_button);
                data_text02 = itemView.findViewById(R.id.textView02);
                // 이벤트 처리는 여기에서
                data_btn.setOnClickListener(new View.OnClickListener(){
                    int i = 0;
                    @Override
                    public void onClick(View v){
                        if(i == 0) {
                            data_text02.setVisibility(View.VISIBLE);
                            i = 1;
                        } else {
                            data_text02.setVisibility(View.GONE);
                            i = 0;
                        }
                    }
                });
            }
        }
    }
}