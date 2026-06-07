package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {

    private TextView textViewSummary;
    private Button buttonConfirm;
    private Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        textViewSummary = findViewById(R.id.textViewSummary);
        buttonConfirm = findViewById(R.id.buttonConfirm);
        buttonCancel = findViewById(R.id.buttonCancel);

        Intent intent = getIntent();

        String name =
                intent.getStringExtra(MainActivity.EXTRA_NAME);

        String ticket =
                intent.getStringExtra(MainActivity.EXTRA_TICKET);

        boolean meal =
                intent.getBooleanExtra(
                        MainActivity.EXTRA_MEAL,
                        false
                );

        String summary =
                "Imię: " + name +
                        "\nTyp biletu: " + ticket +
                        "\nPosiłek: " + (meal ? "Tak" : "Nie");

        textViewSummary.setText(summary);

        buttonConfirm.setOnClickListener(v -> {

            setResult(RESULT_OK);
            finish();

        });

        buttonCancel.setOnClickListener(v -> {

            setResult(RESULT_CANCELED);
            finish();

        });
    }
}