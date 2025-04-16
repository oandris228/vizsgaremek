package com.example.teawebshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.teawebshop.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String TAG = "MainActivity";
    private SharedPreferences sharedPreferences;
    private TextView tvWelcomeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            // 1. SharedPreferences inicializálása
            sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

            // 2. Bejelentkezés ellenőrzése
            checkLoginStatus();

            // 3. ViewBinding inicializálása
            initializeViewBinding();

            // 4. Felhasználói adatok betöltése
            loadUserData();

            // 5. Alapértelmezett fragment beállítása
            setupDefaultFragment();

            // 6. Bottom navigation beállítása
            setupBottomNavigation();

        } catch (Exception e) {
            Log.e(TAG, "Error in onCreate: " + e.getMessage());
            handleError();
        }
    }

    private void checkLoginStatus() {
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (!isLoggedIn) {
            redirectToLogin();
        }
    }

    private void redirectToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void initializeViewBinding() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Welcome TextView inicializálása
        tvWelcomeUser = binding.getRoot().findViewById(R.id.tvWelcomeUser);
    }

    private void loadUserData() {
        String username = sharedPreferences.getString("username", "Felhasználó");
        if (tvWelcomeUser != null) {
            tvWelcomeUser.setText("Üdv, " + username + "!");
        }
    }

    private void setupDefaultFragment() {
        replaceFragment(new HomeFragment());
    }

    private void setupBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            Fragment selectedFragment = null;

            if (id == R.id.home) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.profile) {
                selectedFragment = new ProfileFragment();
            } else if (id == R.id.cart) {
                selectedFragment = new SettingsFragment();
            }

            if (selectedFragment != null) {
                replaceFragment(selectedFragment);
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            // Egyszerűsített animációk
            transaction.setCustomAnimations(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out,
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
            );

            transaction.replace(R.id.frame_layout, fragment);

            // BackStack kezelése
            if (!(fragment instanceof HomeFragment)) {
                transaction.addToBackStack(fragment.getClass().getSimpleName());
            }

            transaction.commit();
        } catch (Exception e) {
            Log.e(TAG, "Error replacing fragment: " + e.getMessage());
        }
    }

    private void handleError() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }




    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }


}