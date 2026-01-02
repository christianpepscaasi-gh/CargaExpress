package com.example.cargaexpress;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SelectVehicle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_vehicle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button selectButton = findViewById(R.id.selectButton);

        Button buttonBackToMain = findViewById(R.id.buttonBackToMain);
        buttonBackToMain.setOnClickListener(view -> {
            Intent intent = new Intent(SelectVehicle.this, MainActivity.class);
            startActivity(intent);
            finish(); // Optional: prevent going back to confirmation
        });
        // Inside onClick of the "Next" button
        Button nextButton = findViewById(R.id.selectButton);
        RadioGroup radioGroup = findViewById(R.id.radioGroupVehicle);

        nextButton.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();

            if (selectedId != -1) {
                RadioButton selectedRadio = findViewById(selectedId);
                String priceString = selectedRadio.getTag().toString(); // "80", "150", etc.

                // Pass it to the AddDeliveryAddressActivity first
                Intent intent = new Intent(SelectVehicle.this, DeliveryActivity.class);
                intent.putExtra("vehicle_price", priceString); // store as string or int
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please select a vehicle", Toast.LENGTH_SHORT).show();
            }
        });

    }
}