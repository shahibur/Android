package com.example.lenovo.imageviewpiccasso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {


    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.imageview);

        Picasso.with(this).
                load("https://vignette.wikia.nocookie.net/hayday/images/7/7d/Apple.png/revision/latest?cb=20150713085424").
                placeholder(R.drawable.error_image).
                error(R.drawable.error_image).resize(250,200).rotate(180).into(imageView);
    }
}
