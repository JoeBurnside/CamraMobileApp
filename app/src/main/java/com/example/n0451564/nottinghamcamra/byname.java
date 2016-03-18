package com.example.n0451564.nottinghamcamra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class byname extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_byname);
        final EditText editText = (EditText) this.findViewById(R.id.editText);
        Button buttonSearch = (Button) this.findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent search = new Intent(byname.this, Results.class);
                search.putExtra("Searchterm", editText.getText().toString());
                startActivity(search);
            }
        });
    }

    protected void onResume() {
        super.onResume();
        final EditText editText = (EditText) this.findViewById(R.id.editText);
        editText.setText("");
    }
}

