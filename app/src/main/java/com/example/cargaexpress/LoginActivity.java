package com.example.cargaexpress;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText emailField = findViewById(R.id.emailInput);
        EditText passwordField = findViewById(R.id.passwordInput);
        Button loginButton = findViewById(R.id.loginButton);

        // "Create Account" text goes to SignupActivity
        findViewById(R.id.createAccountText).setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        });

        // Login button click
        loginButton.setOnClickListener(view -> {
            String email = emailField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email and password must not be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            // TODO: Add actual authentication logic here

            // Proceed if input is valid
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
