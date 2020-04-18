package com.example.android.transcripthub;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class OutgoingBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        Log.d("outgoing call", intent.toString() + ", call to: " + phoneNumber);
        Toast.makeText(context, "Outgoing call catched: " + phoneNumber, Toast.LENGTH_LONG).show();
        Intent target = new Intent(context, HomeActivity.class);
        target.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        target.putExtra("number",phoneNumber);
        context.startActivity(target);

    }
}
