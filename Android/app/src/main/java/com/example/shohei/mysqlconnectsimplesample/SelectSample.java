package com.example.shohei.mysqlconnectsimplesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class SelectSample extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper databaseHelper;
    EditText u_id_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sample);

        databaseHelper = new DatabaseHelper(this);
        u_id_edit = (EditText)findViewById(R.id.u_id);

        findViewById(R.id.select).setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        String method;
        String u_id;
        String result;

        switch(v.getId()) {
            case R.id.select:
                method = "SELECT";
                u_id = u_id_edit.getText().toString();
                String[] attrs = {"u_id", "u_name", "u_email", "u_phone", "u_pass"};
                result = "";

                try {
                    result = databaseHelper.execute(method, u_id).get();

                    JSONObject jobj = new JSONObject(result);
                    JSONArray jarr = jobj.getJSONArray("data");

                    result = "";
                    for(int i = 0; i < jarr.length(); i++) {
                        JSONObject temp = jarr.getJSONObject(i);
                        for(int j = 0; j < attrs.length; j++) {
                            result += attrs[j] + " : " + temp.getString(attrs[j]) + "\n";
                        }
                        result += "\n";
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

                ((TextView)findViewById(R.id.select_result)).setText(result);
                break;

            case R.id.back:
                intent = new Intent(SelectSample.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
