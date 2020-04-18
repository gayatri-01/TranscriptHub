package com.example.android.transcripthub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        final List<String> al = new ArrayList<String>();
        final List<String> tl = new ArrayList<String>();
        final List<String> cl = new ArrayList<String>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference(email.substring(0,email.indexOf('@')));
        DatabaseReference chatsRef = rootRef;
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String c = ds.child("chat").getValue(String.class);
                    String t = ds.child("type").getValue(String.class);
                    String time = ds.child("timestamp").getValue(String.class);
                    String number = ds.child("number").getValue(String.class);
                    al.add(number+" ~~ "+time);
                    tl.add(t);
                    cl.add(c);

                    //Log.d("TAG", c + " / " + t  + " / " + time);
                    //Toast. makeText(getApplicationContext(),c + " / " + t  + " / " + time,Toast. LENGTH_SHORT).show();

                }
                String[] chatTimeArray = new String[al.size()];
                chatTimeArray = al.toArray(chatTimeArray);


                ArrayAdapter adapter = new ArrayAdapter<String>(HistoryActivity.this,
                        R.layout.activity_listview, chatTimeArray);

                ListView listView = (ListView) findViewById(R.id.time_list);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Get the selected item text from ListView
                        String selectedItem = (String) parent.getItemAtPosition(position);
                        Intent intent= new Intent(HistoryActivity.this,HistoryDetailsActivity.class);
                        intent.putExtra("type",tl.get(position));
                        intent.putExtra("chat",cl.get(position));

                        startActivity(intent);

                        // Display the selected item text on TextView
                        //tv.setText("Your favorite : " + selectedItem);
                    }
                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        chatsRef.addListenerForSingleValueEvent(valueEventListener);

    }
}
