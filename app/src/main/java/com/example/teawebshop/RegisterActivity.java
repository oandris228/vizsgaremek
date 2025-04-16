package com.example.teawebshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    // View elemek
    private EditText etEmail, etPassword, etUsername;
    private Button btnRegister;
    private TextView btnBackToLogin; // Vissza gomb TextView-ként

    // Adattárolás
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // View-ok inicializálása
        initViews();

        // SharedPreferences inicializálása
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        // Eseménykezelők beállítása
        setupListeners();



    }

    private void initViews() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etUsername = findViewById(R.id.etUsername);
        btnRegister = findViewById(R.id.btnRegister);
        btnBackToLogin = findViewById(R.id.btnBackToLogin); // Vissza gomb inicializálása
    }

    private void setupListeners() {
        // Regisztráció gomb
        btnRegister.setOnClickListener(v -> registerUser());

        // Vissza gomb
        btnBackToLogin.setOnClickListener(v -> goBackToLogin());
    }

    private void registerUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String username = etUsername.getText().toString().trim();

        if (isInputValid(email, password, username)) {
            saveUserCredentials(email, password, username);
            showRegistrationSuccess();
            redirectToLoginScreen();
        }
    }

    private boolean isInputValid(String email, String password, String username) {
        boolean valid = true;

        // Felhasználónév validáció
        if (username.isEmpty()) {
            etUsername.setError("Felhasználónév megadása kötelező");
            valid = false;
        }

        // Email validáció
        if (email.isEmpty()) {
            etEmail.setError("Email megadása kötelező");
            valid = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Érvénytelen email formátum");
            valid = false;
        }

        // Jelszó validáció
        if (password.isEmpty()) {
            etPassword.setError("Jelszó megadása kötelező");
            valid = false;
        } else if (password.length() < 6) {
            etPassword.setError("Legalább 6 karakter hosszúnak kell lennie");
            valid = false;
        }

        return valid;
    }

    private void saveUserCredentials(String email, String password, String username) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putString("username", username);
        editor.putBoolean("isLoggedIn", true);
        editor.apply();
    }

    private void showRegistrationSuccess() {
        Toast.makeText(this, "Sikeres regisztráció!", Toast.LENGTH_SHORT).show();
    }

    private void redirectToLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void goBackToLogin() {
        finish();
    }
}