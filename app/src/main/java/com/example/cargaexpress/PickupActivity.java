package com.example.cargaexpress;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PickupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pickup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button selectButton = findViewById(R.id.selectButton);

        EditText spinnerTo = findViewById(R.id.spinnerTO);

        selectButton.setOnClickListener(view -> {
            String text = spinnerTo.getText().toString().trim();
            if (text.isEmpty()) {
                Toast.makeText(this, "Please enter a reference number.", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(PickupActivity.this, OnTheWay.class);
                intent.putExtra("location", text);
                startActivity(intent);
                finish();
            }
        });

        Button buttonBackToMain = findViewById(R.id.buttonBackToMain);
        buttonBackToMain.setOnClickListener(view -> {
            Intent intent = new Intent(PickupActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Optional: prevent going back to confirmation
        });
    }
}