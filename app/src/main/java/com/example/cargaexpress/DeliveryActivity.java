package com.example.cargaexpress;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DeliveryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delivery);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spinnerFrom = findViewById(R.id.spinnerFrom);
        Spinner spinnerTo = findViewById(R.id.spinnerTo);
        Button selectButton = findViewById(R.id.selectButton);

// Populate spinner values
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.locations_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        Intent receivedIntent = getIntent();
        String vehiclePrice = receivedIntent.getStringExtra("vehicle_price");

        selectButton.setOnClickListener(view -> {
            String from = spinnerFrom.getSelectedItem().toString();
            String to = spinnerTo.getSelectedItem().toString();

            if (from.equals("Select Location") || to.equals("Select Location")) {
                Toast.makeText(this, "Please select both 'From' and 'To' locations.", Toast.LENGTH_SHORT).show();
            } else if (from.equals(to)) {
                Toast.makeText(this, "'From' and 'To' locations cannot be the same.", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(DeliveryActivity.this, AddDeliveryAddressActivity.class);
                intent.putExtra("from_location", from);
                intent.putExtra("to_location", to);
                intent.putExtra("vehicle_price", vehiclePrice);
                startActivity(intent);
            }
        });



        Button buttonBackToMain = findViewById(R.id.buttonBackToMain);
        buttonBackToMain.setOnClickListener(view -> {
            Intent intent = new Intent(DeliveryActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Optional: prevent going back to confirmation
        });


    }
}