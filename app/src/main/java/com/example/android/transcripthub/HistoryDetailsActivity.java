package com.example.android.transcripthub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HistoryDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);
        Bundle bundle=getIntent().getExtras();
        String type=bundle.get("type").toString();
        String chat=bundle.get("chat").toString();

        String[] chatArr = chat.split(";");
        String[] typeArr = type.split(";");
        //Toast. makeText(getApplicationContext(),type+" "+chat,Toast. LENGTH_SHORT).show();

        LinearLayout myLinearLayout = findViewById(R.id.myLinearLayout);

        for (int i = 1; i < chatArr.length; i++) {
            // create a new textview
            final TextView rowTextView = new TextView(this);

            // set some properties of rowTextView or something
            String temp = typeArr[i];
            if(temp.equals("1")){
                rowTextView.setBackgroundResource(R.drawable.textviewdesign);
                rowTextView.setTextColor(getResources().getColor(R.color.white));
            }else{
                rowTextView.setBackgroundResource(R.drawable.textviewdesign2);

            }
            rowTextView.setText(chatArr[i]);
            rowTextView.setWidth(800);

            rowTextView.setTextSize(20);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(30,30,30,0);
            rowTextView.setLayoutParams(params);
            rowTextView.setPadding(5, 5, 5, 5);

            // add the textview to the linearlayout
            myLinearLayout.addView(rowTextView);


        }
    }
}
