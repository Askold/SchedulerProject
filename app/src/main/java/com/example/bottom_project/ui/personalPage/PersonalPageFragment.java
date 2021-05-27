package com.example.bottom_project.ui.personalPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottom_project.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PersonalPageFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    FirebaseUser user;
    Button btnSignOut;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_personal_page, container, false);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        user = auth.getCurrentUser();
        btnSignOut = root.findViewById(R.id.btnSignOut);

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



        return root;
    }
}