package com.example.lenovo.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FirstFragment extends Fragment {

    private final static String TAG="LogToTracing";
    public final static String MESSAGE="message";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_first, container, false);

        TextView textView=view.findViewById(R.id.textView3);

        Bundle bundle=getArguments();

        textView.setText(bundle.getString(MESSAGE));
        Log.i(TAG,"onCreateView");

       return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.i(TAG,"onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG,"onDetach");
    }

    public static FirstFragment newInstance(String msg) {
        
        Bundle args = new Bundle();
        args.putString(MESSAGE,msg);

        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
    }
}
