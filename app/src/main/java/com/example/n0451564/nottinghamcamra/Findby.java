package com.example.n0451564.nottinghamcamra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Findby extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findby);
        Button buttonByName = (Button) this.findViewById(R.id.buttonByName);
        Button buttonByFacility = (Button) this.findViewById(R.id.buttonByFacility);
        buttonByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Findby.this, byname.class));
            }
        });
        buttonByFacility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Findby.this, ByFacility.class));
            }
        });
    }
}
