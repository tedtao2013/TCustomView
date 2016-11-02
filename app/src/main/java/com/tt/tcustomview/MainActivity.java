package com.tt.tcustomview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;

import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // test
    private RecyclerView mRecPages;
    private List<String> titles;
    {
        titles = new ArrayList<>();
        titles.add("Welcome");
        titles.add("RandomNumber");
        titles.add("default");
        titles.add("View4");
        titles.add("View5");
        titles.add("View6");
        titles.add("View7");
        titles.add("View8");
        titles.add("View9");
        titles.add("View10");
        titles.add("View11");
        titles.add("View12");
        titles.add("View13");
        titles.add("View14");
        titles.add("View15");
        titles.add("View16");
        titles.add("View17");
        titles.add("View18");
        titles.add("View19");
        titles.add("View20");
        titles.add("View21");
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mRecPages = (RecyclerView) findViewById(R.id.rec_pages);
        mRecPages.setLayoutManager(new LinearLayoutManager(this));
        mRecPages.setAdapter(new MyAdapter(this, titles));
    }

    class MyAdapter extends RecyclerView.Adapter<VH> implements View.OnClickListener {

        private Context context;
        private List<String> datas;

        MyAdapter (Context context, List<String> data) {
            this.context = context;
            this.datas = data;
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_main_pages_recyclerview, parent, false);
            VH holder = new VH(view);
            view.setOnClickListener(this);
            return holder;
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            holder.mTitle.setText(datas.get(position));
        }

        @Override
        public int getItemCount() {
            if (datas != null) {
                return datas.size();
            }
            return 0;
        }

        @Override
        public void onClick(View v) {
            int position = mRecPages.getChildAdapterPosition(v);
            Intent intent = new Intent(MainActivity.this, CustomViewActivity.class);
            intent.putExtra("page" , position);
            startActivity(intent);
        }
    }

    class VH extends RecyclerView.ViewHolder {
        TextView mTitle;

        VH(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
