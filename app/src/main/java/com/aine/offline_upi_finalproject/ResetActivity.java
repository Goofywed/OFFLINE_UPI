package com.aine.offline_upi_finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class ResetActivity extends AppCompatActivity {

    private Spinner spinnerQuestions1, spinnerQuestions2, spinnerQuestions3;
    private EditText answerEditText1, answerEditText2, answerEditText3;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        spinnerQuestions1 = findViewById(R.id.s1);
        spinnerQuestions2 = findViewById(R.id.s2);
        spinnerQuestions3 = findViewById(R.id.s3);
        answerEditText1 = findViewById(R.id.editTextSecurityAnswer1);
        answerEditText2 = findViewById(R.id.editTextSecurityAnswer2);
        answerEditText3 = findViewById(R.id.editTextSecurityAnswer3);
        submitButton = findViewById(R.id.btnSubmit);

        setupSpinner(spinnerQuestions1);
        setupSpinner(spinnerQuestions2);
        setupSpinner(spinnerQuestions3);
        setupSubmitButton();
    }

    private void setupSpinner(Spinner spinner) {
        List<String> questions = new ArrayList<>();
        questions.add("What is your grandfather’s last name?");
        questions.add("What’s your home address?");
        questions.add("What is your mother’s phone number?");
        questions.add("What was the name of your first childhood friend?");
        questions.add("What is your brightest childhood dream?");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, questions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setupSubmitButton() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question1 = spinnerQuestions1.getSelectedItem().toString();
                String question2 = spinnerQuestions2.getSelectedItem().toString();
                String question3 = spinnerQuestions3.getSelectedItem().toString();
                String answer1 = answerEditText1.getText().toString();
                String answer2 = answerEditText2.getText().toString();
                String answer3 = answerEditText3.getText().toString();

                // Retrieve answers from SharedPreferences
                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                String storedAnswer1 = preferences.getString("Answer1", "");
                String storedAnswer2 = preferences.getString("Answer2", "");
                String storedAnswer3 = preferences.getString("Answer3", "");

                String storedQuestion1 =preferences.getString("Question1","");
                String storedQuestion2 =preferences.getString("Question2","");
                String storedQuestion3 =preferences.getString("Question3","");

                // Validate answers

                    if ((answer1.equals(storedAnswer1) && question1.equals(storedQuestion1)) ){
                        if(answer2.equals(storedAnswer2) && question2.equals(storedQuestion2)){
                            if (answer3.equals(storedAnswer3) && question3.equals(storedQuestion3)){
                                startActivity(new Intent(ResetActivity.this, Set_PinActivity.class));
                                finish();
                            }
                        }
                    }
                    else {
                        // Answers are incorrect
                        Toast.makeText(ResetActivity.this, "Incorrect answers, please try again", Toast.LENGTH_SHORT).show();
                    }
                }

        });
    }
}



