package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText descriptionInput, amountInput;
    Button addButton;
    ListView listView;
    TextView totalText;

    ArrayList<String> expensesList;
    ArrayAdapter<String> adapter;
    double total = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        descriptionInput = findViewById(R.id.descriptionInput);
        amountInput = findViewById(R.id.amountInput);
        addButton = findViewById(R.id.addButton);
        listView = findViewById(R.id.listView);
        totalText = findViewById(R.id.totalText);

        expensesList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expensesList);
        listView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String desc = descriptionInput.getText().toString();
                String amtStr = amountInput.getText().toString();

                if (!desc.isEmpty() && !amtStr.isEmpty()) {
                    double amt = Double.parseDouble(amtStr);
                    total += amt;
                    expensesList.add(desc + " : ₹" + amt);
                    adapter.notifyDataSetChanged();
                    totalText.setText("Total: ₹" + total);

                    descriptionInput.setText("");
                    amountInput.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Enter description and amount", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
