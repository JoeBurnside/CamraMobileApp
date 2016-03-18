package com.example.n0451564.nottinghamcamra;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Results extends AppCompatActivity {
    String result = "";
    InputStream is = null;
    ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>(2);
    List<String> ids = new ArrayList<String>();
    List<String> values = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Bundle extras = getIntent().getExtras();
        String searchterm = extras.getString("Searchterm");
        filllist task = new filllist();
        task.execute(searchterm);

    }
    private class filllist extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String searchterm = params[0];

            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(new StringBuilder().append("http://joeburnside.comli.com/mobile/search.php?searchterm=").append(searchterm).toString());
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection " + e.toString());
            }
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();

                result = sb.toString();
            } catch (Exception e) {
                Log.e("log_tag", "Error converting result " + e.toString());
            }

            return null;
        }
        protected void onPostExecute(String param){
            try {
                if(!result.startsWith("null")) {
                    JSONArray jArray = new JSONArray(result);
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        String Name = (json_data.getString("Name"));
                        String Location = json_data.getString("Location");
                        HashMap<String, String> publist;
                        publist = new HashMap<String, String>();
                        publist.put("line1", Name);
                        publist.put("line2", Location);
                        list.add(publist);
                        String[] from = {"line1", "line2"};

                        int[] to = {android.R.id.text1, android.R.id.text2};

                        SimpleAdapter adapter = new SimpleAdapter(Results.this, list, android.R.layout.simple_list_item_2, from, to);
                        ids.add(json_data.getString("id"));
                        ListView listView = (ListView) Results.this.findViewById(R.id.pubsList);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position,
                                                    long id) {
                                Intent search = new Intent(Results.this, Pubview.class);
                                String pubID = ids.get(position);
                                search.putExtra("pubID", pubID);
                                startActivity(search);

                            }
                        });
                    }
                }
                    else{
                    String empty = "No Results";
                    values.add(empty);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Results.this,
                            android.R.layout.simple_list_item_1, android.R.id.text1, values);
                    ListView listView = (ListView) Results.this.findViewById(R.id.pubsList);
                    listView.setAdapter(adapter);
                    }
                }
             catch (JSONException e) {
                Log.e("log_tag", "Error parsing data " + e.toString());

            }


        }
    }


}
