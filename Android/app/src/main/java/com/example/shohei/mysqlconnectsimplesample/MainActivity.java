package com.example.shohei.mysqlconnectsimplesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.select_sample).setOnClickListener(this);
        findViewById(R.id.insert_sample).setOnClickListener(this);
        findViewById(R.id.update_sample).setOnClickListener(this);
        findViewById(R.id.delete_sample).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch(v.getId()) {
            case R.id.select_sample:
                intent = new Intent(MainActivity.this, SelectSample.class);
                startActivity(intent);
                break;

            case R.id.insert_sample:
                intent = new Intent(MainActivity.this, InsertSample.class);
                startActivity(intent);
                break;

            case R.id.update_sample:
                intent = new Intent(MainActivity.this, UpdateSample.class);
                startActivity(intent);
                break;

            case R.id.delete_sample:
                intent = new Intent(MainActivity.this, DeleteSample.class);
                startActivity(intent);
                break;
        }
    }

}
