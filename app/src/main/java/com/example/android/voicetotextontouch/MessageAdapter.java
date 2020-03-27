package com.example.android.voicetotextontouch;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private ArrayList<Message> messages;
    private static final int TYPE_ONE = 1;
    private static final int TYPE_TWO = 2;
    private static final int TYPE_THREE = 3;
    public MessageAdapter(ArrayList<Message>  messages) {
        this. messages =  messages;
    }

    // determine which layout to use for the row


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        if (viewType == TYPE_ONE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
            return new ViewHolder(view);
        } else if (viewType == TYPE_TWO) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message2, parent, false);
            return new ViewHolder(view);
        }
        else if (viewType == TYPE_THREE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message3, parent, false);
            return new ViewHolder(view);
        }else {
            throw new RuntimeException("The type has to be ONE, TWO or THREE");
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message mess =  messages.get(position);

        holder.name.setText(mess.getName());
        //holder.description.setText(city.getDescription());


    }

    @Override
    public int getItemCount() {
        if ( messages != null) {
            return  messages.size();
        } else {
            return 0;
        }
    }
    // determine which layout to use for the row
    @Override
    public int getItemViewType(int position) {
        Message item = messages.get(position);
        if (item.getType() == 1) {
            return TYPE_ONE;
        } else if (item.getType() == 2) {
            return TYPE_TWO;
        } else if (item.getType() == 3){
            return TYPE_THREE;
        }else {
            return -1;
        }
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView name;
       // public final TextView description;
       //public final ImageView image;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById(R.id.name);
            //description = view.findViewById(R.id.description);
            //image = view.findViewById(R.id.image);
        }
    }
}