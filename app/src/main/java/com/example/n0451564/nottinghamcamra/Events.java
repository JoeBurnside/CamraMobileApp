package com.example.n0451564.nottinghamcamra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Events extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        Button buttonWeekly = (Button) this.findViewById(R.id.buttonByName);
        Button buttonByThisweek = (Button) this.findViewById(R.id.buttonThisweek);

        buttonWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Events.this, Weekly.class));
            }
        });
        buttonByThisweek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Events.this, ThisWeek.class));
            }
        });
    }

}
