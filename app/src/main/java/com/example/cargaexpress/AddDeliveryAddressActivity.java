package com.example.cargaexpress;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddDeliveryAddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_delivery_address);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button buttonBackToMain = findViewById(R.id.saveAddressButton);
        TextView textFrom = findViewById(R.id.textFrom);
        TextView textTo = findViewById(R.id.textTo);
        Intent receivedIntent = getIntent();
        String vehiclePrice = receivedIntent.getStringExtra("vehicle_price");
        String fromLocation = receivedIntent.getStringExtra("from_location");
        String toLocation = receivedIntent.getStringExtra("to_location");
        textFrom.setText("From: " + fromLocation);
        textTo.setText("To: " + toLocation);


        buttonBackToMain.setOnClickListener(view -> {
            EditText nameField = findViewById(R.id.contactName);
            EditText phoneField = findViewById(R.id.contactNumber);
            EditText addressField = findViewById(R.id.addressDetails);
            RadioGroup labelGroup = findViewById(R.id.addressLabelGroup);

            String name = nameField.getText().toString().trim();
            String phone = phoneField.getText().toString().trim();
            String address = addressField.getText().toString().trim();
            int selectedLabelId = labelGroup.getCheckedRadioButtonId();

            if (name.isEmpty() || phone.isEmpty() || address.isEmpty() || selectedLabelId == -1) {
                Toast.makeText(this, "Please fill out all required fields and select a label.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!phone.matches("\\d{10,11}")) {
                Toast.makeText(this, "Enter a valid phone number (10–11 digits)", Toast.LENGTH_SHORT).show();
                return;
            }

            // All good, go to Payment activity
            Intent intent = new Intent(AddDeliveryAddressActivity.this, Payment.class);
            intent.putExtra("vehicle_price", vehiclePrice);
            startActivity(intent);
            finish();
        });
    }
}