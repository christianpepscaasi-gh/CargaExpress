package com.example.cargaexpress;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText emailInput = findViewById(R.id.emailInput);
        EditText fullNameInput = findViewById(R.id.fullNameInput);
        EditText usernameInput = findViewById(R.id.usernameInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        EditText repeatPasswordInput = findViewById(R.id.repeatPasswordInput);
        Button createAccountButton = findViewById(R.id.createAccountButton);

        createAccountButton.setOnClickListener(view -> {
            String email = emailInput.getText().toString().trim();
            String fullName = fullNameInput.getText().toString().trim();
            String username = usernameInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            String repeatPassword = repeatPasswordInput.getText().toString().trim();

            if (email.isEmpty() || fullName.isEmpty() || username.isEmpty() ||
                    password.isEmpty() || repeatPassword.isEmpty()) {
                Toast.makeText(this, "All fields must be filled.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(repeatPassword)) {
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Optional: Add more validations here like email format, password length, etc.

            // Proceed to main activity
            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
