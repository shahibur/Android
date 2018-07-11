package com.example.lenovo.imageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {



    ImageView imageView;
    TextView textView_name,textView_describe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView=findViewById(R.id.icon_image);

        textView_describe=findViewById(R.id.textView_decr);
        textView_name=findViewById(R.id.textView_name);


        ////Circular ImageView

        final Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.shawon01);
        RoundedBitmapDrawable roundedBitmapDrawable= RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        roundedBitmapDrawable.setCircular(true);

        imageView.setImageDrawable(roundedBitmapDrawable);

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                View view= getLayoutInflater().inflate(R.layout.dialog_custom,null);

                PhotoView photoView=view.findViewById(R.id.image_layout);

                photoView.setImageResource(R.drawable.shawon01);

                builder.setView(view);

                AlertDialog alertDialog=builder.create();

                alertDialog.show();
            }
        });

    }
}
