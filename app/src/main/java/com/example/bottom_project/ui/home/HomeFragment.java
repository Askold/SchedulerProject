package com.example.bottom_project.ui.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottom_project.Models.MyEvent;
import com.example.bottom_project.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.rengwuxian.materialedittext.MaterialEditText;

public class HomeFragment extends Fragment {

    FloatingActionButton btnNewEvent;
    private HomeViewModel homeViewModel;

    private RelativeLayout rela;
    private FirebaseRecyclerAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        btnNewEvent = root.findViewById(R.id.btnNewEvent);

        btnNewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewEvent();
            }
        });

        RecyclerView listOfEvents = root.findViewById(R.id.list_of_events);
        listOfEvents.setLayoutManager(new LinearLayoutManager(requireContext()));
        Query query = FirebaseDatabase.getInstance().getReference().child("Events");

        FirebaseRecyclerOptions<MyEvent> options =
                new FirebaseRecyclerOptions.Builder<MyEvent>()
                        .setQuery(query, MyEvent.class)
                        .build();
        adapter = new EventsAdapter(options);
        listOfEvents.setAdapter(adapter);
        //adapter.startListening();


        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    private void setNewEvent() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext());
        dialog.setTitle("Новое мероприятие");
        dialog.setMessage("Введите данные нового мероприятия");
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View add_new_event = inflater.inflate(R.layout.add_event_window, null);
        dialog.setView(add_new_event);


        final MaterialEditText place = add_new_event.findViewById(R.id.placeField);
        final MaterialEditText name = add_new_event.findViewById(R.id.nameField);
        final MaterialEditText date = add_new_event.findViewById(R.id.dateField);
        final MaterialEditText time = add_new_event.findViewById(R.id.timeField);

        dialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (TextUtils.isEmpty(name.getText().toString())){
                    Snackbar.make(rela, "Введите название", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(place.getText().toString())){
                    Snackbar.make(rela, "Введите место", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(date.getText().toString())){
                    Snackbar.make(rela, "Введите дату проведения", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(time.getText().toString())){
                    Snackbar.make(rela, "Введите время", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                FirebaseDatabase.getInstance().getReference().child("Events").push().setValue(
                        new MyEvent(name.getText().toString(),
                                place.getText().toString(),
                                date.getText().toString(),
                                time.getText().toString()));
            }
        });
        dialog.show();
    }

}