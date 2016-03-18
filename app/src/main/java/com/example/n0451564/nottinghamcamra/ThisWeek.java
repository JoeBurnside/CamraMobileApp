package com.example.n0451564.nottinghamcamra;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ThisWeek extends AppCompatActivity {
    String result = "";
    InputStream is = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this_week);
        filllist task = new filllist();
        task.execute();

    }
    private class filllist extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://joeburnside.comli.com/mobile/searchthisweek.php");
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
                ArrayList<EventsList> arrayOfEvents = new ArrayList<EventsList>();
                EventsAdapter adapter = new EventsAdapter(ThisWeek.this, arrayOfEvents);
                ListView listView = (ListView) findViewById(R.id.pubsList);
                listView.setAdapter(adapter);
                JSONArray jArray = new JSONArray(result);
                ArrayList<EventsList> newEvents = EventsList.fromJson(jArray);
                adapter.addAll(newEvents);
            } catch(
                    JSONException e
                    )

            {
                Log.e("log_tag", "Error parsing data " + e.toString());
            }


        }
    }
    public class EventsAdapter extends ArrayAdapter<EventsList> {
        public EventsAdapter(Context context, ArrayList<EventsList> eventslist) {
            super(context, 0, eventslist);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            EventsList eventslist = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.mylistview, parent, false);
            }
            TextView names = (TextView) convertView.findViewById(R.id.textView2);
            TextView events = (TextView) convertView.findViewById(R.id.textView3);
            TextView days = (TextView) convertView.findViewById(R.id.textView4);
            TextView times = (TextView) convertView.findViewById(R.id.textView5);
            names.setText(eventslist.names);
            events.setText(eventslist.events);
            days.setText(eventslist.days);
            times.setText(eventslist.times);
            return convertView;
        }
    }

}