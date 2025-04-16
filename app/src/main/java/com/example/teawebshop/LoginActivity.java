package com.example.teawebshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private SharedPreferences sharedPreferences;

    // Alapértelmezett bejelentkezési adatok
    private static final String DEFAULT_EMAIL = "acs@gmail.com";
    private static final String DEFAULT_PASSWORD = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // View elemek inicializálása
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        // Alapértelmezett felhasználó létrehozása, ha még nem létezik
        createDefaultUser();

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (validateInputs(email, password)) {
                if (authenticateUser(email, password)) {
                    saveUserData(email);
                    navigateToMain();
                } else {
                    Toast.makeText(this, "Hibás email vagy jelszó", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }

    private void createDefaultUser() {
        if (!sharedPreferences.contains("email")) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email", DEFAULT_EMAIL);
            editor.putString("password", DEFAULT_PASSWORD);
            editor.putString("username", "Alapértelmezett Felhasználó");
            editor.putBoolean("isLoggedIn", false);
            editor.apply();

            Toast.makeText(this, "Alapértelmezett felhasználó létrehozva", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateInputs(String email, String password) {
        boolean isValid = true;

        if (email.isEmpty()) {
            etEmail.setError("Email megadása kötelező");
            isValid = false;
        }

        if (password.isEmpty()) {
            etPassword.setError("Jelszó megadása kötelező");
            isValid = false;
        }

        return isValid;
    }

    private boolean authenticateUser(String email, String password) {
        String savedEmail = sharedPreferences.getString("email", "");
        String savedPassword = sharedPreferences.getString("password", "");
        return email.equals(savedEmail) && password.equals(savedPassword);
    }

    private void saveUserData(String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", true);
        editor.putString("email", email);
        editor.apply();
    }

    private void navigateToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}