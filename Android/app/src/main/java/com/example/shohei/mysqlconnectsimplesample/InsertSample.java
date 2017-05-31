package com.example.shohei.mysqlconnectsimplesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertSample extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper databaseHelper;
    EditText u_name_edit;
    EditText u_email_edit;
    EditText u_phone_edit;
    EditText u_pass_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_sample);

        databaseHelper = new DatabaseHelper(this);
        u_name_edit = (EditText)findViewById(R.id.u_name);
        u_email_edit = (EditText)findViewById(R.id.u_email);
        u_phone_edit = (EditText)findViewById(R.id.u_phone);
        u_pass_edit = (EditText)findViewById(R.id.u_pass);

        findViewById(R.id.insert).setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        String method;
        String u_name;
        String u_email;
        String u_phone;
        String u_pass;

        switch(v.getId()) {
            case R.id.insert:
                method = "INSERT";
                u_name = u_name_edit.getText().toString();
                u_email = u_email_edit.getText().toString();
                u_phone = u_phone_edit.getText().toString();
                u_pass = u_pass_edit.getText().toString();

                if(!u_name.equals("") && !u_email.equals("") && !u_phone.equals("") && !u_pass.equals("")) {
                    databaseHelper.execute(method, u_name, u_email, u_phone, u_pass);
                }
                else {
                    Toast.makeText(this, "Please Fill Out Form", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.back:
                intent = new Intent(InsertSample.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
