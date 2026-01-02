package com.example.cargaexpress;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get the vehicle price from previous activity
        Intent intent1 = getIntent();
        String vehiclePrice = intent1.getStringExtra("vehicle_price");

        TextView priceText = findViewById(R.id.textPrice);
        priceText.setText("Delivery Fee: ₱" + vehiclePrice);

        RadioGroup paymentGroup = findViewById(R.id.paymentMethodGroup);
        EditText accountNumber = findViewById(R.id.num);
        Button buttonConfirm = findViewById(R.id.saveAddressButton);

        buttonConfirm.setOnClickListener(view -> {
            int selectedId = paymentGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(Payment.this, "Please select a payment method.", Toast.LENGTH_SHORT).show();
                return;
            }

            String accountNum = accountNumber.getText().toString().trim();
            if (accountNum.isEmpty()) {
                Toast.makeText(Payment.this, "Please enter account number.", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedRadio = findViewById(selectedId);
            String paymentMethod = selectedRadio.getText().toString();

            // Show Toast
            Toast.makeText(Payment.this, "Payment confirmed via " + paymentMethod, Toast.LENGTH_SHORT).show();

            // Proceed to confirmation screen
            Intent intent = new Intent(Payment.this, ConfirmationActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
