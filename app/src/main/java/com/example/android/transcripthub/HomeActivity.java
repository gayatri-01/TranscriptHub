package com.example.android.transcripthub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
static String number = "1234";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle bundle=getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey("number")) {
                number = bundle.get("number").toString();
                if (number != null) {
                    Toast.makeText(getApplicationContext(), number, Toast.LENGTH_LONG).show();
                }
            }
        }

// Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Button button=(Button)findViewById(R.id.button);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("English");
        categories.add("Hindi");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(HomeActivity.this,MainActivity.class);
                intent.putExtra("data",String.valueOf(spinner.getSelectedItem()));
                intent.putExtra("number",number);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
public void showHistory(View view){
    Intent i=new Intent(HomeActivity.this,
            HistoryActivity.class);
    //Intent is used to switch from one activity to another.

    startActivity(i);
    //invoke the SecondActivity.

    finish();
    //the current activity will get finished.


}
}
