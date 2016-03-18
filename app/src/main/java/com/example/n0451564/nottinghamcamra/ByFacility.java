package com.example.n0451564.nottinghamcamra;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class ByFacility extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_facility);
        final CheckBox checkBoxcask = (CheckBox) this.findViewById(R.id.checkBoxcask);
        final CheckBox checkBoxcider = (CheckBox) this.findViewById(R.id.checkBoxcider);
        final CheckBox checkBoxfood = (CheckBox) this.findViewById(R.id.checkBoxfood);
        final CheckBox checkBoxchildren = (CheckBox) this.findViewById(R.id.checkBoxchildren);
        final CheckBox checkBoxdogs = (CheckBox) this.findViewById(R.id.checkBoxdogs);
        final CheckBox checkBoxdisabled = (CheckBox) this.findViewById(R.id.checkBoxdisabled);
        final CheckBox checkBoxbaby = (CheckBox) this.findViewById(R.id.checkBoxbaby);
        final CheckBox checkBoxoutside = (CheckBox) this.findViewById(R.id.checkBoxoutside);
        final CheckBox checkBoxwifi = (CheckBox) this.findViewById(R.id.checkBoxwifi);
        final CheckBox checkBoxdiscount = (CheckBox) this.findViewById(R.id.checkBoxdiscount);
        Button buttonSearch = (Button) this.findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent search = new Intent(ByFacility.this, ResultsFac.class);
                if(checkBoxcask.isChecked()){
                    search.putExtra("Cask", "true");
                }
                else{
                    search.putExtra("Cask", "false");
                }
                if(checkBoxcider.isChecked()){
                    search.putExtra("Cider", "true");
                }
                else{
                    search.putExtra("Cider", "false");
                }
                if(checkBoxfood.isChecked()){
                    search.putExtra("Food", "true");
                }
                else{
                    search.putExtra("Food", "false");
                }
                if(checkBoxchildren.isChecked()){
                    search.putExtra("Children", "true");
                }
                else{
                    search.putExtra("Children", "false");
                }
                if(checkBoxdogs.isChecked()){
                    search.putExtra("Dogs", "true");
                }
                else{
                    search.putExtra("Dogs", "false");
                }
                if(checkBoxdisabled.isChecked()){
                    search.putExtra("Disabled", "true");
                }
                else{
                    search.putExtra("Disabled", "false");
                }
                if(checkBoxbaby.isChecked()){
                    search.putExtra("Baby", "true");
                }
                else{
                    search.putExtra("Baby", "false");
                }
                if(checkBoxoutside.isChecked()){
                    search.putExtra("Outside", "true");
                }
                else{
                    search.putExtra("Outside", "false");
                }
                if(checkBoxwifi.isChecked()){
                    search.putExtra("Wifi", "true");
                }
                else{
                    search.putExtra("Wifi", "false");
                }
                if(checkBoxdiscount.isChecked()){
                    search.putExtra("Discount", "true");
                }
                else{
                    search.putExtra("Discount", "false");
                }
                startActivity(search);
            }
        });
    }
    protected void onResume() {
        super.onResume();
        final CheckBox checkBoxcask = (CheckBox) this.findViewById(R.id.checkBoxcask);
        final CheckBox checkBoxcider = (CheckBox) this.findViewById(R.id.checkBoxcider);
        final CheckBox checkBoxfood = (CheckBox) this.findViewById(R.id.checkBoxfood);
        final CheckBox checkBoxchildren = (CheckBox) this.findViewById(R.id.checkBoxchildren);
        final CheckBox checkBoxdogs = (CheckBox) this.findViewById(R.id.checkBoxdogs);
        final CheckBox checkBoxdisabled = (CheckBox) this.findViewById(R.id.checkBoxdisabled);
        final CheckBox checkBoxbaby = (CheckBox) this.findViewById(R.id.checkBoxbaby);
        final CheckBox checkBoxoutside = (CheckBox) this.findViewById(R.id.checkBoxoutside);
        final CheckBox checkBoxwifi = (CheckBox) this.findViewById(R.id.checkBoxwifi);
        final CheckBox checkBoxdiscount = (CheckBox) this.findViewById(R.id.checkBoxdiscount);
        checkBoxcask.setChecked(false);
        checkBoxcider.setChecked(false);
        checkBoxfood.setChecked(false);
        checkBoxchildren.setChecked(false);
        checkBoxdogs.setChecked(false);
        checkBoxdisabled.setChecked(false);
        checkBoxbaby.setChecked(false);
        checkBoxoutside.setChecked(false);
        checkBoxwifi.setChecked(false);
        checkBoxdiscount.setChecked(false);
    }

}
