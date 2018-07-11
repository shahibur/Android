package com.example.lenovo.retrofitfetchingjsonfromurl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listview);

        Retrofit builder=new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api=builder.create(Api.class);

       /// Call<list<Hero>>call = api.getHerosData();

        Call<List<Hero>>call=api.getHerosData();


        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {

                List<Hero>heroes=response.body();

                String [] names=new String[heroes.size()];

                for(int i=0; i<heroes.size(); i++)
                {
                    names[i]=heroes.get(i).getName();
                    names[i]=heroes.get(i).getRealname();
                }
                listView.setAdapter(new ArrayAdapter<String>(getApplication(),R.layout.support_simple_spinner_dropdown_item,names));
              /*  for(Hero h: heroes)
                {
                    Log.d("name ",h.getName());
                    Log.d("realname ",h.getRealname());
                    Log.d("imageurl ",h.getImageurl());
                }*/
            }
            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {

                Toast.makeText(getApplication(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
