package com.example.lenovo.retrofitcopy.recycleAdopter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.retrofitcopy.R;
import com.example.lenovo.retrofitcopy.recycleHolder.RecycleHolder;
import com.example.lenovo.retrofitcopy.model.User;

import java.util.List;

/**
 * Created by Lenovo on 1/7/2018.
 */

public class RecycleAdopter extends RecyclerView.Adapter<RecycleHolder>{

    private List<User>userList;

    public RecycleAdopter(List<User>userList)
    {
        this.userList=userList;
    }
    @Override
    public RecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_layout,null);

        RecycleHolder recycleHolder=new RecycleHolder(layoutView);

        return recycleHolder;
    }

    @Override
    public void onBindViewHolder(RecycleHolder holder, int position) {

        holder.name.setText((CharSequence) userList.get(position).getName());
        holder.hobby.setText((CharSequence) userList.get(position).getHobby());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
