package com.example.lenovo.chathomes;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lenovo.mychat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabsPagerAdapter tabsPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth=FirebaseAuth.getInstance();

        viewPager=findViewById(R.id.main_viewPager);

        toolbar=findViewById(R.id.main_page_toolbar);

        try {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("myChat");
        } catch (Exception e) {
            e.printStackTrace();
        }
        tabsPagerAdapter=new TabsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabsPagerAdapter);
        tabLayout=findViewById(R.id.main_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void onStart() {
        super.onStart();


        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        if(firebaseUser==null)
        {
            LogOutUser();
        }

    }

    private void LogOutUser() {


        Intent startPageActivity=new Intent(MainActivity.this,StartPageActivity.class);

        startPageActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(startPageActivity);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId()==R.id.main_logout_button)
        {
            mAuth.signOut();
            LogOutUser();
        }
        if(item.getItemId()==R.id.main_account_settings)
        {
            Intent SettingsActivity=new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(SettingsActivity);

        }
        if(item.getItemId()==R.id.allUser_button)
        {
            Intent AllUsersActivity=new Intent(MainActivity.this, AllUsersActivity.class);
            startActivity(AllUsersActivity);

        }
        return true;
    }
}
