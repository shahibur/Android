package com.example.lenovo.retrofitpractice01.recycleHolder;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;
import com.example.lenovo.retrofitpractice01.R;

/**
 * Created by Lenovo on 1/7/2018.
 */

public class RecyclerViewHolder extends ViewHolder implements View.OnClickListener{


    public TextView name,hobby;


    public RecyclerViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        name=itemView.findViewById(R.id.name);
        hobby=itemView.findViewById(R.id.hobby);
    }
    @Override
    public void onClick(View v) {

    }
}
