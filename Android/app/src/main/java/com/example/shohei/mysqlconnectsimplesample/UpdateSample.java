package com.example.shohei.mysqlconnectsimplesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class UpdateSample extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper databaseHelper;
    EditText u_id_edit;
    EditText u_new_name_edit;
    EditText u_new_email_edit;
    EditText u_new_phone_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sample);

        databaseHelper = new DatabaseHelper(this);
        u_id_edit = (EditText)findViewById(R.id.u_id);
        u_new_name_edit = (EditText)findViewById(R.id.u_new_name);
        u_new_email_edit = (EditText)findViewById(R.id.u_new_email);
        u_new_phone_edit = (EditText)findViewById(R.id.u_new_phone);

        findViewById(R.id.search).setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.update).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        String method;
        String u_id;
        String u_new_name;
        String u_new_email;
        String u_new_phone;
        String result;

        switch(v.getId()) {
            case R.id.search:
                method = "SELECT";
                u_id = u_id_edit.getText().toString();

                HashMap<String, String> result_arr = new HashMap<>();
                String[] attrs = {"u_name", "u_email", "u_phone"};
                int[] attrs_id = {R.id.u_new_name, R.id.u_new_email, R.id.u_new_phone};

                if(!u_id.equals("")) {
                    try {
                        result = databaseHelper.execute(method, u_id).get();

                        JSONObject jobj = new JSONObject(result);
                        JSONArray jarr = jobj.getJSONArray("data");
                        jobj = jarr.getJSONObject(0);

                        for(int i = 0; i < attrs.length; i++) {
                            result_arr.put(attrs[i], jobj.getString(attrs[i]));
                        }
                        for(int i = 0; i < attrs_id.length; i++) {
                            ((EditText)findViewById(attrs_id[i])).setText(result_arr.get(attrs[i]));
                        }
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(this, "Fill In ID", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.back:
                intent = new Intent(UpdateSample.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.update:
                method = "UPDATE";
                u_id = u_id_edit.getText().toString();
                u_new_name = u_new_name_edit.getText().toString();
                u_new_email = u_new_email_edit.getText().toString();
                u_new_phone = u_new_phone_edit.getText().toString();

                if(!u_id.equals("")) {
                    databaseHelper.execute(method, u_id, u_new_name, u_new_email, u_new_phone);
                }
                else {
                    Toast.makeText(this, "Fill In ID", Toast.LENGTH_LONG).show();
                }
                break;

        }
    }
}
