package com.example.teawebshop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    // View elemek
    private TextView tvUsername, tvEmail;
    private Button btnLogout, btnEditProfile;
    private SharedPreferences sharedPreferences;

    // Konstansok
    private static final String PREFS_NAME = "login";
    private static final String DEFAULT_USERNAME = "Felhasználó";
    private static final String DEFAULT_EMAIL = "email@example.com";



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        try {
            tvUsername = view.findViewById(R.id.tvUsername);
            tvEmail = view.findViewById(R.id.tvEmail);
            btnLogout = view.findViewById(R.id.btnLogout);
            btnEditProfile = view.findViewById(R.id.btnEditProfile);
        } catch (NullPointerException e) {
            Toast.makeText(getActivity(), "Hiba a felület betöltésében", Toast.LENGTH_SHORT).show();
            requireActivity().onBackPressed();
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (tvUsername != null && tvEmail != null && btnLogout != null && btnEditProfile != null) {
            loadUserData();
            setupButtonListeners();
        }
    }

    private void loadUserData() {
        try {
            String username = sharedPreferences.getString("username", DEFAULT_USERNAME);
            String email = sharedPreferences.getString("email", DEFAULT_EMAIL);

            tvUsername.setText(username);
            tvEmail.setText(email);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Hiba az adatok betöltésében", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupButtonListeners() {
        btnLogout.setOnClickListener(v -> {
            try {
                logoutUser();
            } catch (Exception e) {
                Toast.makeText(getActivity(), "Hiba a kijelentkezés során", Toast.LENGTH_SHORT).show();
            }
        });

        btnEditProfile.setOnClickListener(v -> {
            try {
                editProfile();
            } catch (Exception e) {
                Toast.makeText(getActivity(), "Hiba a profil szerkesztése során", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void logoutUser() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Toast.makeText(getActivity(), "Sikeres kijelentkezés", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        requireActivity().finish();
    }

    private void editProfile() {

        Toast.makeText(getActivity(), "Profil szerkesztése", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onDetach() {
        // Clean up
        tvUsername = null;
        tvEmail = null;
        btnLogout = null;
        btnEditProfile = null;
        super.onDetach();
    }
}
