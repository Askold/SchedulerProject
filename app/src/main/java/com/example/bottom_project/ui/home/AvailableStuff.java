package com.example.bottom_project.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.bottom_project.MyEvent;
import com.example.bottom_project.MyEventAdapter;
import com.example.bottom_project.R;


public class AvailableStuff extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_available_stuff, container, false);
        MyWorker[] stuff = makeStuff();
        MyStuffAdapter adapter = new MyStuffAdapter(requireContext(), stuff);
        ListView lv = (ListView) root.findViewById(R.id.list);
        lv.setAdapter(adapter);
        return root;
    }

    MyWorker[] makeStuff(){
        String[] surnames = {};
        String[] names = {};
        MyWorker[] stuff = new MyWorker[10];
        MyWorker worker;
        for (int i = 0; i < stuff.length; i++){
            worker = new MyWorker(surnames[i], names[i]);
            stuff[i] = worker;
        }
        return stuff;
    }

}