package com.example.n0451564.nottinghamcamra;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Pubview extends AppCompatActivity {

    String result = "";
    InputStream is = null;
    List<String> values = new ArrayList<String>();
    ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>(2);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pubview);
        Bundle extras = getIntent().getExtras();
        final String pubID = extras.getString("pubID");
        fillinfo task = new fillinfo();
        task.execute(pubID);
        filllist task2 = new filllist();
        task2.execute(pubID);
        String URL = new StringBuilder().append("http://joeburnside.comli.com/mobile/images/").append(pubID).append(".jpg").toString();
        LoadImageFromURL task3 = new LoadImageFromURL();
        task3.execute(URL);
        TextView textViewMap = (TextView)Pubview.this.findViewById(R.id.textViewMap);
        SpannableString content = new SpannableString("Show On Map");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textViewMap.setText(content);
        textViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent search = new Intent(Pubview.this, Map.class);
                search.putExtra("pubID", pubID);
                startActivity(search);
            }
        });
    }
    private class fillinfo extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String searchterm = params[0];

            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(new StringBuilder().append("http://joeburnside.comli.com/mobile/searchpub.php?pubID=").append(searchterm).toString());
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
                JSONArray jArray = new JSONArray(result);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    TextView textViewName = (TextView) Pubview.this.findViewById(R.id.textViewName);
                    TextView textViewLocation = (TextView) Pubview.this.findViewById(R.id.textViewLocation);
                    TextView textViewOpening = (TextView) Pubview.this.findViewById(R.id.textViewOpening);
                    TextView textViewTelNo = (TextView) Pubview.this.findViewById(R.id.textViewTelNo);
                    TextView textViewDescription = (TextView) Pubview.this.findViewById(R.id.textViewDescription);
                    TextView textViewFacilities = (TextView) Pubview.this.findViewById(R.id.textViewFacilities);
                    textViewName.setText(json_data.getString("Name").toString());
                    textViewLocation.setText(json_data.getString("Location").toString());
                    textViewOpening.setText(json_data.getString("Opening").toString());
                    textViewTelNo.setText(json_data.getString("TelNo").toString());
                    textViewDescription.setText(json_data.getString("Description").toString());
                    String Cask = json_data.getString("Cask").toString();
                    String Cider = json_data.getString("Cider").toString();
                    String Food = json_data.getString("Food").toString();
                    String Children = json_data.getString("Children").toString();
                    String Dogs = json_data.getString("Dogs").toString();
                    String Disabled = json_data.getString("Disabled").toString();
                    String Baby = json_data.getString("Baby").toString();
                    String Outside = json_data.getString("Outside").toString();
                    String Wifi = json_data.getString("Wifi").toString();
                    String Discount = json_data.getString("Discount").toString();
                    String Facilities = FacilitiesString(Cask, Cider, Food, Children, Dogs, Disabled, Baby, Outside, Wifi, Discount);
                    textViewFacilities.setText(Facilities);

                }
            } catch (JSONException e) {
                Log.e("log_tag", "Error parsing data " + e.toString());
            }


        }
    }
    private class filllist extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String searchterm = params[0];

            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(new StringBuilder().append("http://joeburnside.comli.com/mobile/searchevents.php?pubID=").append(searchterm).toString());
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
                        String event = (json_data.getString("Event"));
                        String day = json_data.getString("Day");
                        String time = json_data.getString("Time");
                        time = time.substring(0, time.length() - 3);
                        String timeday = new StringBuilder().append(day).append(" ").append(time).toString();
                        HashMap<String, String> eventlist;
                        eventlist = new HashMap<String, String>();
                        eventlist.put("line1", event);
                        eventlist.put("line2", timeday);
                        list.add(eventlist);
                        String[] from = {"line1", "line2"};

                        int[] to = {android.R.id.text1, android.R.id.text2};

                        SimpleAdapter adapter = new SimpleAdapter(Pubview.this, list, android.R.layout.simple_list_item_2, from, to);

                        ListView listView = (ListView) Pubview.this.findViewById(R.id.eventList);
                        listView.setAdapter(adapter);
                    }
                }
                else{
                    String empty = "No Events";
                    values.add(empty);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Pubview.this,
                            android.R.layout.simple_list_item_1, android.R.id.text1, values);
                    ListView listView = (ListView) Pubview.this.findViewById(R.id.eventList);
                    listView.setAdapter(adapter);
                }
            } catch (JSONException e) {
                Log.e("log_tag", "Error parsing data " + e.toString());
            }


        }

    }
    public class LoadImageFromURL extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... params) {
            String URL = params[0];
            try {
                URL url = new URL(URL);
                InputStream is = url.openConnection().getInputStream();
                Bitmap bitMap = BitmapFactory.decodeStream(is);
                return bitMap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            ImageView imgview = (ImageView) Pubview.this.findViewById(R.id.imageViewPub);
            imgview.setImageBitmap(result);
        }

    }
    public String FacilitiesString(String Cask, String Cider, String Food, String Children, String Dogs, String Disabled, String Baby, String Outside, String Wifi, String Discount){
        StringBuilder Facilities = new StringBuilder();
        if (Cask.contains("TRUE")) {
            if (Facilities.length() < 1){
                Facilities.append("Cask Ale");
            }
            else{
                Facilities.append(", Cask Ale");
            }
        }
        if (Cider.contains("TRUE")){
            if (Facilities.length() < 1){
                Facilities.append("Real Cider");
            }
            else{
                Facilities.append(", Real Cider");
            }
        }
        if (Food.contains("TRUE")){
            if (Facilities.length() < 1){
                Facilities.append("Food");
            }
            else{
                Facilities.append(", Food");
            }
        }
        if (Children.contains("TRUE")){
            if (Facilities.length() < 1){
                Facilities.append("Children Welcome");
            }
            else{
                Facilities.append(", Children Welcome");
            }
        }
        if (Dogs.contains("TRUE")){
            if (Facilities.length() < 1){
                Facilities.append("Dogs Welcome");
            }
            else{
                Facilities.append(", Dogs Welcome");
            }
        }
        if (Disabled.contains("TRUE")){
            if (Facilities.length() < 1){
                Facilities.append("Disabled Access");
            }
            else{
                Facilities.append(", Disabled Access");
            }
        }
        if (Baby.contains("TRUE")){
            if (Facilities.length() < 1){
                Facilities.append("Baby Changing");
            }
            else{
                Facilities.append(", Baby Changing");
            }
        }
        if (Outside.contains("TRUE")){
            if (Facilities.length() < 1){
                Facilities.append("Outside Area");
            }
            else{
                Facilities.append(", Outside Area");
            }
        }
        if (Wifi.contains("TRUE")){
            if (Facilities.length() < 1){
                Facilities.append("Free WiFi");
            }
            else{
                Facilities.append(", Free WiFi");
            }
        }
        if (Discount.contains("TRUE")){
            if (Facilities.length() < 1){
                Facilities.append("CAMRA Discount");
            }
            else{
                Facilities.append(", CAMRA Discount");
            }
        }
        return Facilities.toString();
    }

}
