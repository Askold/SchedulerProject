package com.example.bottom_project.ui.messenger;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottom_project.Models.Message;
import com.example.bottom_project.R;
import com.example.bottom_project.ui.home.EventsAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import android.text.format.DateFormat;

public class MessagesAdapter extends FirebaseRecyclerAdapter<Message, MessagesAdapter.myviewholder> {
    public MessagesAdapter(FirebaseRecyclerOptions<Message> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MessagesAdapter.myviewholder holder, int i, @NonNull Message model) {
        holder.mess_name.setText(model.getUserName());
        holder.mess_time.setText(DateFormat.format("dd-mm-yyyy HH:MM:SS", model.getMessageTime()));
        holder.mess_text.setText(model.getTextMessage());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_messages, parent, false);
        return new MessagesAdapter.myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView mess_name, mess_time, mess_text;
        public myviewholder(@NonNull View v){
            super(v);
            mess_name = v.findViewById(R.id.message_user);
            mess_time = v.findViewById(R.id.message_time);
            mess_text = v.findViewById(R.id.message_text);
        }
    }
}
