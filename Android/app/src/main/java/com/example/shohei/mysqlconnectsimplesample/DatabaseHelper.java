package com.example.shohei.mysqlconnectsimplesample;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class DatabaseHelper extends AsyncTask<String,Void,String> {

    Context context;

    DatabaseHelper(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String mysql_url = "http://10.0.1.19/misao/combination/MysqlConnectSimpleSample.php";
        String method = params[0];

        HttpURLConnection httpURLConnection;

        if(method.equals("SELECT")) {
            String u_id = params[1];

            try {
                URL url = new URL(mysql_url);

                httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data = URLEncoder.encode("SELECT", "UTF-8")+"="+URLEncoder.encode("OK", "UTF-8")+"&"+
                        URLEncoder.encode("u_id", "UTF-8")+"="+URLEncoder.encode(u_id, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null) {
                    sb.append(line+"\n");
                }
                String result = sb.toString();
                bufferedReader.close();
                is.close();

                httpURLConnection.disconnect();

                return result;
            }
            catch(MalformedURLException e) {
                e.printStackTrace();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }

        else if(method.equals("INSERT")) {
            String u_name = params[1];
            String u_email = params[2];
            String u_phone = params[3];
            String u_pass = params[4];

            try {
                URL url = new URL(mysql_url);

                httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data = URLEncoder.encode("INSERT", "UTF-8")+"="+URLEncoder.encode("OK", "UTF-8")+"&"+
                        URLEncoder.encode("u_name", "UTF-8")+"="+URLEncoder.encode(u_name, "UTF-8")+"&"+
                        URLEncoder.encode("u_email", "UTF-8")+"="+URLEncoder.encode(u_email, "UTF-8")+"&"+
                        URLEncoder.encode("u_phone", "UTF-8")+"="+URLEncoder.encode(u_phone, "UTF-8")+"&"+
                        URLEncoder.encode("u_pass", "UTF-8")+"="+URLEncoder.encode(u_pass, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                is.close();

                return "Insert Success";
            }
            catch(MalformedURLException e) {
                e.printStackTrace();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }

        else if(method.equals("UPDATE")) {
            String u_id = params[1];
            String u_name = params[2];
            String u_email = params[3];
            String u_phone = params[4];

            try {
                URL url = new URL(mysql_url);

                httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data = URLEncoder.encode("UPDATE", "UTF-8")+"="+URLEncoder.encode("OK", "UTF-8")+"&"+
                        URLEncoder.encode("u_id", "UTF-8")+"="+URLEncoder.encode(u_id, "UTF-8")+"&"+
                        URLEncoder.encode("u_name", "UTF-8")+"="+URLEncoder.encode(u_name, "UTF-8")+"&"+
                        URLEncoder.encode("u_email", "UTF-8")+"="+URLEncoder.encode(u_email, "UTF-8")+"&"+
                        URLEncoder.encode("u_phone", "UTF-8")+"="+URLEncoder.encode(u_phone, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                is.close();

                return "Update Success";
            }
            catch(MalformedURLException e) {
                e.printStackTrace();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }

        else if(method.equals("DELETE")) {
            String u_id = params[1];

            try {
                URL url = new URL(mysql_url);

                httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data = URLEncoder.encode("DELETE", "UTF-8")+"="+URLEncoder.encode("OK", "UTF-8")+"&"+
                        URLEncoder.encode("u_id", "UTF-8")+"="+URLEncoder.encode(u_id, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                is.close();

                return "Delete Success";
            }
            catch(MalformedURLException e) {
                e.printStackTrace();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if(this.isJSONValid(result)) {
            Toast.makeText(context, "Select Success", Toast.LENGTH_LONG).show();
        }
        else {
            if (result.equals("Select Success")) {
                Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            } else if (result.equals("Insert Success")) {
                Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            } else if (result.equals("Update Success")) {
                Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            } else if (result.equals("Delete Success")) {
                Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "FAILED CONNECT", Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
            return true;
        }
        catch(JSONException e) {
            return false;
        }
    }

}
