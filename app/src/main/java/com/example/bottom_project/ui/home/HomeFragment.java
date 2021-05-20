package com.example.bottom_project.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottom_project.MyEvent;
import com.example.bottom_project.MyEventAdapter;
import com.example.bottom_project.R;

import java.sql.Date;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        MyEvent[] events = makeEvents();
        MyEventAdapter adapter = new MyEventAdapter(requireContext(), events);
        ListView lv = (ListView) root.findViewById(R.id.list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            //int id =  ((MyEvent)parent.getItemAtPosition(position)).id;
            Intent i = new Intent(Intent.ACTION_VIEW);
            startActivity(i);
        });

        return root;
    }

    MyEvent[] makeEvents() {
        String[] names = {"event1", "event2",  "event3", "event4","event5", "event6", "event7",  "event8", "event9","event10"};
        MyEvent[] events = new MyEvent[10];
        MyEvent event;
        for (int i = 0; i < events.length; i++){
            event = new MyEvent(names[i]);
            events[i] = event;
        }
        return events;
    }
}