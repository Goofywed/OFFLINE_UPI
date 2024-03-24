package com.aine.offline_upi_finalproject;//package com.aine.offline_upi_finalproject;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.security.keystore.KeyGenParameterSpec;
//import android.security.keystore.KeyProperties;
//import android.util.Base64;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;

import java.nio.charset.StandardCharsets;
import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//
//public class Set_PinActivity extends AppCompatActivity {
//
//    private EditText pinEditText;
//    private EditText confirmPinEditText;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_set_pin2);
//
//        pinEditText = findViewById(R.id.editTextPin);
//        confirmPinEditText = findViewById(R.id.editTextConfirmPin);
//        Button submitButton = findViewById(R.id.btnSubmit);
//
//        submitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String enteredPin = pinEditText.getText().toString();
//                String confirmedPin = confirmPinEditText.getText().toString();
//
//                if (enteredPin.length() != 4) {
//                    // PIN length is not 4 digits
//                    Toast.makeText(Set_PinActivity.this, "PIN must be 4 digits", Toast.LENGTH_SHORT).show();
//                } else if (!enteredPin.equals(confirmedPin)) {
//                    // PINs don't match
//                    Toast.makeText(Set_PinActivity.this, "PINs do not match", Toast.LENGTH_SHORT).show();
//                } else {
//                    // PINs match and have a valid length (exactly 4 digits)
//                    storePin(enteredPin);
//                    Toast.makeText(Set_PinActivity.this, "PIN set successfully", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(Set_PinActivity.this, MainActivity.class));
//                    finish(); // Finish the activity after setting the PIN
//                }
//            }
//        });
//    }
//
//    private void storePin(String pin) {
//        // Store the PIN using SharedPreferences
//        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("PIN", pin);
//        editor.apply();
//    }
//
//}














//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class Set_PinActivity extends AppCompatActivity {
//
//    private EditText pinEditText;
//    private Spinner spinnerQuestions1, spinnerQuestions2, spinnerQuestions3;
//    private EditText answerEditText1, answerEditText2, answerEditText3;
//    private Button submitButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_set_pin2);
//
//        pinEditText = findViewById(R.id.editTextPin);
//        spinnerQuestions1 = findViewById(R.id.spinnerQuestion1);
//        spinnerQuestions2 = findViewById(R.id.spinnerQuestion2);
//        spinnerQuestions3 = findViewById(R.id.spinnerQuestion3);
//        answerEditText1 = findViewById(R.id.editTextAnswer1);
//        answerEditText2 = findViewById(R.id.editTextAnswer2);
//        answerEditText3 = findViewById(R.id.editTextAnswer3);
//        submitButton = findViewById(R.id.btnSubmit);
//
//        setupSpinner();
//        setupSubmitButton();
//    }
//
//    private void setupSpinner() {
//        List<String> questions = new ArrayList<>();
//        questions.add("What is your grandfather’s last name?");
//        questions.add("What’s your home address?");
//        questions.add("What is your mother’s phone number?");
//        questions.add("What was the name of your first childhood friend?");
//        questions.add("What is your brightest childhood dream?");
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, questions);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinnerQuestions1.setAdapter(adapter);
//        spinnerQuestions2.setAdapter(adapter);
//        spinnerQuestions3.setAdapter(adapter);
//    }
//
//    private void setupSubmitButton() {
//        submitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String enteredPin = pinEditText.getText().toString();
//                String question1 = spinnerQuestions1.getSelectedItem().toString();
//                String question2 = spinnerQuestions2.getSelectedItem().toString();
//                String question3 = spinnerQuestions3.getSelectedItem().toString();
//                String answer1 = answerEditText1.getText().toString();
//                String answer2 = answerEditText2.getText().toString();
//                String answer3 = answerEditText3.getText().toString();
//
//                if (enteredPin.length() != 4) {
//                    // PIN length is not 4 digits
//                    Toast.makeText(Set_PinActivity.this, "PIN must be 4 digits", Toast.LENGTH_SHORT).show();
//                } else if (answer1.isEmpty() || answer2.isEmpty() || answer3.isEmpty()) {
//                    // Answer is empty
//                    Toast.makeText(Set_PinActivity.this, "Please enter answers for all questions", Toast.LENGTH_SHORT).show();
//                } else {
//                    // PIN and security question/answer are valid
//                    try {
//                        String encryptedPin = EncryptionHelper.encrypt(enteredPin);
//                        storePinAndSecurityQuestions(encryptedPin, question1, answer1, question2, answer2, question3, answer3);
//                        Toast.makeText(Set_PinActivity.this, "PIN and security questions set successfully", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(Set_PinActivity.this, MainActivity.class));
//                        finish(); // Finish the activity after setting the PIN
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        Toast.makeText(Set_PinActivity.this, "Error encrypting PIN", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
//    }
//
//    private void storePinAndSecurityQuestions(String pin, String question1, String answer1, String question2, String answer2, String question3, String answer3) {
//        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("PIN", pin);
//        editor.putString("Question1", question1);
//        editor.putString("Answer1", answer1);
//        editor.putString("Question2", question2);
//        editor.putString("Answer2", answer2);
//        editor.putString("Question3", question3);
//        editor.putString("Answer3", answer3);
//        editor.apply();
//    }
//}





import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Set_PinActivity extends AppCompatActivity {

    private EditText pinEditText;
    private EditText confirmPinEditText;

    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pin2);

        confirmPinEditText = findViewById(R.id.editTextConfirmPin);
        pinEditText = findViewById(R.id.editTextPin);
        submitButton = findViewById(R.id.btnSubmit); // Initialize submitButton

        setupSubmitButton();
    }

    private void setupSubmitButton() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredPin = pinEditText.getText().toString();
                String confirmedPin = confirmPinEditText.getText().toString(); // Retrieve confirmed PIN

                if (enteredPin.length() != 4 || confirmedPin.length() != 4) {
                    // PIN length is not 4 digits for either PIN
                    Toast.makeText(Set_PinActivity.this, "PIN must be 4 digits", Toast.LENGTH_SHORT).show();
                } else if (!enteredPin.equals(confirmedPin)) {
                    // Entered PIN does not match confirmed PIN
                    Toast.makeText(Set_PinActivity.this, "PINs do not match", Toast.LENGTH_SHORT).show();
                } else {
                    // PIN is valid and confirmed
                    try {
                        String encryptedPin = EncryptionHelper.encrypt(enteredPin);
                        storePinAndSecurityQuestions(encryptedPin);
                        Toast.makeText(Set_PinActivity.this, "PIN and security questions set successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Set_PinActivity.this, SecurityQuestionsActivity.class));
                        finish(); // Finish the activity after setting the PIN
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(Set_PinActivity.this, "Error encrypting PIN", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void storePinAndSecurityQuestions(String pin) {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("PIN", pin);
        editor.apply();
    }
}























