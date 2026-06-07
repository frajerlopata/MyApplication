package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_TICKET = "extra_ticket";
    public static final String EXTRA_MEAL = "extra_meal";

    private EditText editTextName;
    private Spinner spinnerTicket;
    private CheckBox checkBoxMeal;
    private Button buttonNext;

    private final ActivityResultLauncher<Intent> launcher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {

                        if (result.getResultCode() == RESULT_OK) {

                            Toast.makeText(
                                    this,
                                    "Rejestracja potwierdzona",
                                    Toast.LENGTH_LONG
                            ).show();

                        } else {

                            Toast.makeText(
                                    this,
                                    "Rejestracja anulowana",
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        spinnerTicket = findViewById(R.id.spinnerTicket);
        checkBoxMeal = findViewById(R.id.checkBoxMeal);
        buttonNext = findViewById(R.id.buttonNext);

        String[] tickets = {
                "Standard",
                "VIP",
                "Student"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        tickets
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinnerTicket.setAdapter(adapter);

        buttonNext.setOnClickListener(v -> {

            String name =
                    editTextName.getText().toString().trim();

            if (name.isEmpty()) {

                editTextName.setError("Podaj imię");
                return;
            }

            String ticket =
                    spinnerTicket.getSelectedItem().toString();

            boolean meal =
                    checkBoxMeal.isChecked();

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            SummaryActivity.class
                    );

            intent.putExtra(EXTRA_NAME, name);
            intent.putExtra(EXTRA_TICKET, ticket);
            intent.putExtra(EXTRA_MEAL, meal);

            launcher.launch(intent);
        });
    }
}