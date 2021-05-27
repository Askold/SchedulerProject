package com.example.bottom_project.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottom_project.Models.MyEvent;
import com.example.bottom_project.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class EventsAdapter extends FirebaseRecyclerAdapter<MyEvent, EventsAdapter.myviewholder> {

    public EventsAdapter(@NonNull FirebaseRecyclerOptions<MyEvent> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int i, @NonNull MyEvent model) {
        holder.name.setText(model.getName());
        holder.time.setText(model.getTime());
        holder.place.setText(model.getPlace());
        holder.date.setText(model.getDate());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_events, parent, false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView place, name, date, time;
        public myviewholder(@NonNull View v){
            super(v);
            name = v.findViewById(R.id.name);
            place = v.findViewById(R.id.place);
            date = v.findViewById(R.id.date);
            time = v.findViewById(R.id.time);
        }
    }
}
