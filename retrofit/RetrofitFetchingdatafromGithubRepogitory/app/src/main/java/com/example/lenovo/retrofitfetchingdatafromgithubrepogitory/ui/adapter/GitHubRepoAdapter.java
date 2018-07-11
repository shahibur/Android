package com.example.lenovo.retrofitfetchingdatafromgithubrepogitory.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lenovo.retrofitfetchingdatafromgithubrepogitory.R;
import com.example.lenovo.retrofitfetchingdatafromgithubrepogitory.api.model.GitHubRepo;

import java.util.List;

/**
 * Created by Lenovo on 1/9/2018.
 */

public class GitHubRepoAdapter extends ArrayAdapter<GitHubRepo> {


   private Context context;
   private List<GitHubRepo>values;

    public GitHubRepoAdapter(Context context, List<GitHubRepo> values) {
        super(context, R.layout.display_layout_text, values);
        this.context = context;
        this.values = values;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=convertView;

        if(view==null)
        {

            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view=layoutInflater.inflate(R.layout.display_layout_text,parent,false);

        }

        TextView textView=view.findViewById(R.id.textView);

        GitHubRepo gitHubRepo=values.get(position);

        String message=gitHubRepo.getName();

        textView.setText(message);

        return view;
    }

}
