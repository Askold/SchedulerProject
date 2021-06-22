package com.example.bottom_project.ui.personalPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottom_project.Models.MyEvent;
import com.example.bottom_project.R;
import com.example.bottom_project.ui.home.EventsAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalPageFragment extends Fragment {

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    FirebaseUser user;
    Button btnSignOut;
    MaterialTextView email;
    CircleImageView imageView;
    private RelativeLayout relativeLayout;
    private FirebaseRecyclerAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_personal_page, container, false);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        user = auth.getCurrentUser();
        btnSignOut = root.findViewById(R.id.btnSignOut);
        imageView = root.findViewById(R.id.userPhoto);
        email = root.findViewById(R.id.emailField);

        email.setText(user.getEmail());
        //imageView.setImageDrawable(R.drawable.);

        btnSignOut.setOnClickListener(v -> {
            auth.signOut();
        });

        if (FirebaseAuth.getInstance().getCurrentUser() == null){
            Snackbar.make(root, "Вы не авторизованы", Snackbar.LENGTH_SHORT).show();
        }

        if (user != null) {
            // Name, email address, and profile photo Url
            //String name = user.getName();
            String email = user.getEmail();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }

        RecyclerView listOfEvents = root.findViewById(R.id.list_of_personal_events);
        listOfEvents.setLayoutManager(new LinearLayoutManager(requireContext()));
        Query query = FirebaseDatabase.getInstance().getReference().child("Events");

        FirebaseRecyclerOptions<MyEvent> options =
                new FirebaseRecyclerOptions.Builder<MyEvent>()
                        .setQuery(query, MyEvent.class)
                        .build();
        adapter = new UserEventsAdapter(options);
        listOfEvents.setAdapter(adapter);

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
}