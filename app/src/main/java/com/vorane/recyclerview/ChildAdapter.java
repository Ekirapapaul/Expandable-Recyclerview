package com.vorane.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hp on 4/26/2016.
 */
public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildHolder> {
    ArrayList<String> childitems = new ArrayList<>();
    private Context context;

    public ChildAdapter(Context context, ArrayList<String> childitems) {
        this.childitems = childitems;
        this.context = context;
    }


    @Override
    public ChildHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.child, parent, false);
        return new ChildHolder(v);
    }

    @Override
    public void onBindViewHolder(ChildHolder holder, int position) {
        holder.tv_child.setText(childitems.get(position));
    }

    @Override
    public int getItemCount() {
        return childitems.size();
    }

    class ChildHolder extends RecyclerView.ViewHolder {
        TextView tv_child;

        public ChildHolder(View itemView) {
            super(itemView);
            tv_child = (TextView) itemView.findViewById(R.id.tv_child);
        }
    }
}
