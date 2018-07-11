package com.example.lenovo.practice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class FragmentA extends android.app.Fragment {

    Button b;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_, container, false);

        b=view.findViewById(R.id.button5);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funtionClick();
            }
        });


        return view;
    }
    public  void funtionClick()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this.getActivity());

        builder.setTitle("Warning !!");
        builder.setMessage("Are you sure Exit ???");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ///getActivity().finish();
                Toast.makeText(getActivity(),"you just used Toast in app",Toast.LENGTH_SHORT).show();


            }
        });

        builder.setNegativeButton("No",null);
        builder.create();
        builder.show();
    }

}
