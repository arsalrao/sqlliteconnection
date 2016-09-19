package com.boost.entertainment.sqliteapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SaveStateActivity extends AppCompatActivity {

    private int scroll ;

    private StateSaveFragment stateSaveFragment ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_state);

        FragmentManager fragmentManager = getSupportFragmentManager();

        stateSaveFragment = (StateSaveFragment) fragmentManager.findFragmentByTag("data");





        if (stateSaveFragment !=null){
            scroll = stateSaveFragment.getInteger() ;
        }else {

            stateSaveFragment = new StateSaveFragment();
            fragmentManager.beginTransaction().add(stateSaveFragment , "data").commit();
            stateSaveFragment.setInteger(scroll);
            Toast.makeText(SaveStateActivity.this, "integer is null", Toast.LENGTH_SHORT).show();
        }

        ListView listView = (ListView) findViewById(R.id.save_listview);

        ArrayList<String> stringArrayList = getStringArrayList();

        Toast.makeText(SaveStateActivity.this, "created", Toast.LENGTH_SHORT).show();

        ArrayAdapter adapter = new ArrayAdapter(this , android.R.layout.simple_list_item_1 , stringArrayList);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

                scroll += 1 ;

                Log.i("scroll",scroll+"");
            }
        });


        listView.setAdapter(adapter);


    }

    private ArrayList<String> getStringArrayList() {

        ArrayList<String> stringArrayList = new ArrayList<>();

        for (int i = 0 ; i <100 ;i++){

            stringArrayList.add(i+"");

        }
        return stringArrayList ;
    }

    @Override
    protected void onDestroy() {
        stateSaveFragment.setInteger(scroll);
        Toast.makeText(SaveStateActivity.this, "onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
