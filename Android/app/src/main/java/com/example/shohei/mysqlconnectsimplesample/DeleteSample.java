package com.example.shohei.mysqlconnectsimplesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteSample extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper databaseHelper;
    EditText u_id_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_sample);

        databaseHelper = new DatabaseHelper(this);
        u_id_edit = (EditText)findViewById(R.id.u_id);

        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String method;
        String u_id;

        switch(v.getId()) {

            case R.id.delete:
                method = "DELETE";
                u_id = u_id_edit.getText().toString();

                if(!u_id.equals("")) {
                    databaseHelper.execute(method, u_id);
                }
                else {
                    Toast.makeText(this, "Please Fill out ID", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.back:
                Intent intent = new Intent(DeleteSample.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
