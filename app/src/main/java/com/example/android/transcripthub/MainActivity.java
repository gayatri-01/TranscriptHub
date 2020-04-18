package com.example.android.transcripthub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.*;
import java.util.Locale;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class MainActivity extends AppCompatActivity {
    static  int mic = 0;
    static RecyclerView messages;
    private RecyclerView.Adapter adapter;
    static ArrayList<Message> mess;
    static String number = "1234";
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();

        mess = initMessages();

        messages = (RecyclerView) findViewById(R.id.messages);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        messages.setLayoutManager(mLayoutManager);

        adapter = new MessageAdapter(mess);
        messages.setAdapter(adapter);
        //messages.scrollToPosition(mess.size() - 1);

        //final EditText editText = findViewById(R.id.editText);
        //final EditText editText2 = findViewById(R.id.editText2);
        String languagePref = "en";
        Bundle bundle=getIntent().getExtras();
        String data = "";
        if (bundle != null) {
            data=bundle.get("data").toString();
            if(bundle.containsKey("number"))
                number = bundle.get("number").toString();
        }

        //Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
        if(data.equals("Hindi")){
            languagePref="hi";
        }
        final SpeechRecognizer mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);


        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
//                Locale.getDefault());

        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, languagePref);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, languagePref);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, languagePref);
       // mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS,true);


        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

               mSpeechRecognizer.startListening(mSpeechRecognizerIntent);

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                //mSpeechRecognizer.stopListening();

                //getting all the matches
                ArrayList<String> matches = bundle
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                //displaying the first match
                if (matches != null){
                    if(mic  == 0){
                        //editText2.setText(matches.get(0));
                        if(!mess.get(mess.size()-1).equals(new Message(matches.get(0),1)))
                            mess.add(new Message(matches.get(0),1));
                        messages.scrollToPosition(mess.size() - 1);
                        adapter.notifyDataSetChanged();

                    }

                    else{
                        //editText.setText(matches.get(0));
                        if(!mess.get(mess.size()-1).equals(new Message(matches.get(0),2)))
                            mess.add(new Message(matches.get(0),2));
                        messages.scrollToPosition(mess.size() - 1);
                        adapter.notifyDataSetChanged();


                    }

                }


                mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
            }

            @Override
            public void onPartialResults(Bundle bundle) {
//                //getting all the matches
//                ArrayList<String> matches = bundle
//                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
//
//                //displaying the first match
//                if (matches != null){
//                    if(speaker == 1)
//                        editText2.setText(matches.get(0));
//                    else if(mic == 1)
//                        editText.setText(matches.get(0));
//                }
            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        findViewById(R.id.button).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        mic = 0;

                        mSpeechRecognizer.stopListening();

                        //editText.setHint("You will see input here");
                        break;

                    case MotionEvent.ACTION_DOWN:
                        mic = 1;

                        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);

                        //editText.setText("");
                        //editText.setHint("Listening...");
                        break;
                }
                return false;
            }
        });
        findViewById(R.id.button2).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:

                        mic = 1;
                        mSpeechRecognizer.stopListening();

                        //editText2.setHint("You will see input here");
                        break;

                    case MotionEvent.ACTION_DOWN:

                        mic = 0;
                        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);

                        //editText2.setText("");
                        //editText2.setHint("Listening...");
                        break;
                }
                return false;
            }
        });
    }


    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                startActivity(intent);
                finish();
            }
        }
    }
    public void storeChats(View view){
        String email = "xyz@gmail.com";
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
            email = user.getEmail();
        Toast.makeText(getApplicationContext(),email,Toast.LENGTH_LONG).show();
        mDatabase = FirebaseDatabase.getInstance().getReference(email.substring(0,email.indexOf('@')));


        String chatId = mDatabase.push().getKey();
        String c = "";
        String t = "";
        for (int i = 0; i < mess.size(); i++){
            c+=mess.get(i).getName()+";";
            t+=mess.get(i).getType()+";";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh:mm:ss");
        String format = simpleDateFormat.format(new Date());

        Chat instance = new Chat(format, c, t,number);
        mDatabase.child(chatId).setValue(instance);

        Intent i=new Intent(MainActivity.this,
                HomeActivity.class);
        //Intent is used to switch from one activity to another.

        startActivity(i);
        //invoke the SecondActivity.

        finish();
        //the current activity will get finished.

    }

    private ArrayList<Message> initMessages() {
        ArrayList<Message> list = new ArrayList<>();

        list.add(new Message("Begin the conversation!",3));


        return list;
    }
}

