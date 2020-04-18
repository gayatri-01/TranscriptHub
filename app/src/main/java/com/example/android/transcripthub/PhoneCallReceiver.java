package com.example.android.transcripthub;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class PhoneCallReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {

            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){

                Toast.makeText(context,"Ringing State Number is - " + incomingNumber, Toast.LENGTH_SHORT).show();
                Intent target = new Intent(context, HomeActivity.class);
                target.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                target.putExtra("number",incomingNumber);
                context.startActivity(target);

            }
        if ((state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))){
            Toast.makeText(context,"Received State",Toast.LENGTH_SHORT).show();
            Intent target = new Intent(context, HomeActivity.class);
            target.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            target.putExtra("number",incomingNumber);
            context.startActivity(target);
        }




    }
}
