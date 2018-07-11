package com.example.lenovo.retrofitpractice01.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.retrofitpractice01.R;
import com.example.lenovo.retrofitpractice01.recycleHolder.RecyclerViewHolder;
import com.example.lenovo.retrofitpractice01.model.User;

import java.util.List;

/**
 * Created by Lenovo on 1/7/2018.
 */

public class RecylerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {


    private List<User>item;
    public RecylerViewAdapter(List<User>item)
    {
        this.item=item;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_view,null);

       RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(layoutView);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.name.setText((CharSequence) item.get(position).getName());
        holder.hobby.setText((CharSequence) item.get(position).getHobby());
    }

    @Override
    public int getItemCount() {

        return item.size();
    }
}
