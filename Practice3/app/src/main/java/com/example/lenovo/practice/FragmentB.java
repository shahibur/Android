package com.example.lenovo.practice;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentB extends android.app.Fragment {

    EditText ed1,ed2;
    Button submit;
    TextView tv;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =inflater.inflate(R.layout.fragment_fragment_b, container, false);

       ed1=view.findViewById(R.id.editText4);
       ed2=view.findViewById(R.id.editText5);
       tv=view.findViewById(R.id.textView3);
       submit=view.findViewById(R.id.button3);

       submit.setOnClickListener(new View.OnClickListener() {

           public void onClick(View view)
           {
               funtionMethod();
           }

       });


       return  view;
    }

        public  void  funtionMethod()
        {
            String s1,s2,s3;
            Integer n1,n2,n3;
          //  s1=ed1.getText().toString();
           // s2=ed2.getText().toString();
            //s3=s1+s2;
           // tv.setText(" "+s3);

            n1=Integer.parseInt(ed1.getText().toString());
            n2=Integer.parseInt(ed2.getText().toString());
            n3=n1+n2;
            tv.setText(Integer.valueOf(n3).toString());
            Toast.makeText(this.getActivity(),"successfully",Toast.LENGTH_SHORT).show();

        }


}
