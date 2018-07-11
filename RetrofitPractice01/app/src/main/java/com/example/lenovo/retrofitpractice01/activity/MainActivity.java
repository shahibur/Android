package com.example.lenovo.retrofitpractice01.activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.example.lenovo.retrofitpractice01.R;
import com.example.lenovo.retrofitpractice01.adapter.RecylerViewAdapter;
import com.example.lenovo.retrofitpractice01.model.User;
import com.example.lenovo.retrofitpractice01.network.ApiClient;
import com.example.lenovo.retrofitpractice01.service.APIservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUserList();
    }

    private void getUserList() {

        try{
            APIservice apIservice= ApiClient.getRetrofit().create(APIservice.class);
            Call<List<User>>call=apIservice.getUserData();
            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                    Log.d("onRespone",response.message());

                    List<User>userList=response.body();

                    linearLayoutManager=new LinearLayoutManager(MainActivity.this);

                    RecyclerView recyclerView=findViewById(R.id.recycler);

                    recyclerView.setLayoutManager(linearLayoutManager);

                    RecylerViewAdapter recylerViewAdapter=new RecylerViewAdapter(userList);

                    recyclerView.setAdapter(recylerViewAdapter);

                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {

                }
            });

        }catch (Exception e)
        {

        }
    }
}
