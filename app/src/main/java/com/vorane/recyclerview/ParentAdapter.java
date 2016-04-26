package com.vorane.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hp on 4/25/2016.
 */
public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ParentHolder> {
    private static final int TYPE_PARENT = 1;
    private static final int TYPE_CHILD = 2;
    private Context context;
    private ArrayList<String> parentItems = new ArrayList<>();
    private ArrayList<ArrayList<String>> childItems = new ArrayList<>();

    public ParentAdapter(Context context, ArrayList<String> parentItems, ArrayList<ArrayList<String>> childItems) {
        this.context = context;
        this.childItems = childItems;
        this.parentItems = parentItems;
    }


    @Override
    public ParentAdapter.ParentHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.parent, parent, false);
        return new ParentHolder(v);

    }

    @Override
    public void onBindViewHolder(ParentAdapter.ParentHolder holder, int position) {
        holder.tv_parent.setText(parentItems.get(position));
        holder.recyclerView.setLayoutManager(new CustomLinearLayoutManager(context));
        holder.recyclerView.setAdapter(new ChildAdapter(context, childItems.get(position)));

    }

    @Override
    public int getItemCount() {
        return parentItems.size();
    }

    public void expand(ArrayList<String> items, int position) {
        Log.d("Size", "" + items.size());
        childItems.get(position).clear();
        for (int i = 0; i < items.size(); i++) {
            childItems.get(position).add(i, items.get(i));
        }
        //childItems.set(position,items);
        notifyItemChanged(position);
    }

    public void collapse(int position) {

        childItems.get(position).clear();
        notifyItemChanged(position);

    }

    class ParentHolder extends RecyclerView.ViewHolder {
        TextView tv_parent;
        RecyclerView recyclerView;

        public ParentHolder(View itemView) {
            super(itemView);
            tv_parent = (TextView) itemView.findViewById(R.id.tv_parent);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.parent_recyclerview);
        }
    }
}
