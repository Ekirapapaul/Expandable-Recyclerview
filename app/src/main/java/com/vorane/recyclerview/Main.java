package com.vorane.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by hp on 4/25/2016.
 */
public class Main extends AppCompatActivity {
    ArrayList<String> parentItems = new ArrayList<>();
    ParentAdapter adapter;
    private ArrayList<Integer> clicked = new ArrayList<>();
    private ArrayList<ArrayList<String>> childItems = new ArrayList<>();
    private ArrayList<ArrayList<String>> empty_childItems = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setData();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new ParentAdapter(getBaseContext(), parentItems, empty_childItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (clicked.get(position) == 1) {
                    adapter.collapse(position);
                    clicked.set(position, 0);

                } else {
                    adapter.expand(childItems.get(position), position);
                    clicked.set(position, 1);
                }


            }
        }));
        setChildItems();
    }

    private void setData() {
        ArrayList<String> item = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            parentItems.add(i, "Parent " + i);
            empty_childItems.add(i, item);
            clicked.add(i, 0);
        }
    }

    private void setChildItems() {

        for (int i = 0; i < 5; i++) {
            ArrayList<String> items = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                items.add(j, "Child of parent " + i);
            }
            childItems.add(i, items);

        }
        childItems.get(2).add("Child of Parent 2");

    }
}
