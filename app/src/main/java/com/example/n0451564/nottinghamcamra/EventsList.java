package com.example.n0451564.nottinghamcamra;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Joe on 26/02/2016.
 */
public class EventsList {
    public String names;
    public String events;
    public String times;
    public String days;

    public EventsList(String names, String events, String times, String days) {
        this.names = names;
        this.events = events;
        this.times = times;
        this.days = days;

    }

    public EventsList(JSONObject object) {
        try {
            this.names = object.getString("Name");
            this.events = object.getString("Event");
            String time = object.getString("Time");
            time = time.substring(0, time.length() - 3);
            this.times = time;
            this.days = object.getString("Day");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public static ArrayList<EventsList> fromJson(JSONArray jsonObjects) {
        ArrayList<EventsList> events = new ArrayList<EventsList>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                events.add(new EventsList(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return events;
    }

}