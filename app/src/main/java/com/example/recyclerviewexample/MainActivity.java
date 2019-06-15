package com.example.recyclerviewexample;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    ArrayList<RecyclerViewItem> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mAdapter = new RecyclerAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Log.d(TAG, "pos : " + pos);
            }
        });

        addItem(getResources().getDrawable(R.drawable.ic_launcher_background), "title1", "content1");
        addItem(getResources().getDrawable(R.drawable.ic_launcher_background), "title2", "content2");
        addItem(getResources().getDrawable(R.drawable.ic_launcher_background), "title3", "content2");

        mAdapter.notifyDataSetChanged();
    }

    private void addItem(Drawable image, String title, String content) {
        RecyclerViewItem item = new RecyclerViewItem();

        item.setImage(image);
        item.setTitle(title);
        item.setContents(content);

        mList.add(item);
    }
}
