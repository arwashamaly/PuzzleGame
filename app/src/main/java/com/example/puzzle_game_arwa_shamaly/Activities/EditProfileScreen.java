package com.example.puzzle_game_arwa_shamaly.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.puzzle_game_arwa_shamaly.Database.PuzzleViewModel;
import com.example.puzzle_game_arwa_shamaly.Database.User;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import com.example.puzzle_game_arwa_shamaly.databinding.ActivityEditProfileScreenBinding;

import java.util.Calendar;

public class EditProfileScreen extends AppCompatActivity {
    ActivityEditProfileScreenBinding binding;
    String birthdate;
    int id;
    PuzzleViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        model = new ViewModelProvider(this).get(PuzzleViewModel.class);

        Intent intent = getIntent();
        binding.etUserName.setText(intent.getStringExtra("username"));
        binding.etEmail.setText(intent.getStringExtra("email"));
        binding.tvSelectBirthdate.append(intent.getStringExtra("birthdate"));

        birthdate = intent.getStringExtra("birthdate");

        if (intent.getStringExtra("gender").equals("Male")) {
            binding.rbMale.setChecked(true);
        } else {
            binding.rbFemale.setChecked(true);
        }
        binding.tvSelectBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //جلب التاريخ الحالي و جعله الافتراضي
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                if (year < now.get(Calendar.YEAR)) {
                                    binding.tvSelectBirthdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                    birthdate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                } else {
                                    Toast.makeText(EditProfileScreen.this,
                                            "Please choose a valid date", Toast.LENGTH_SHORT).show();
                                }

                            }
                        },
                        now.get(Calendar.YEAR), // Initial year selection
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH) // Inital day selection
                );
                dpd.show(getSupportFragmentManager(), "DatePickerDialog");

            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id = intent.getIntExtra("id", 0);
                String username = binding.etUserName.getText().toString();
                String email = binding.etEmail.getText().toString();
                String country = binding.spCountry.getSelectedItem().toString();
                String gender = intent.getStringExtra("gender");
                if (binding.rbFemale.isChecked()) {
                    gender = "Female";
                } else if (binding.rbMale.isChecked()) {
                    gender = "Male";
                }

                if (username.isEmpty()) {
                    binding.etUserName.setError("please fill this field");
                } else if (email.isEmpty()) {
                    binding.etEmail.setError("please fill this field");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(getBaseContext(), "Check that the email is written correctly",
                            Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(username, email, gender, country, birthdate,
                            intent.getIntExtra("numOfCompletedLevels", 0),
                            intent.getIntExtra("numOfQuestionsAnswered", 0),
                            intent.getIntExtra("numOfCorrectAnswer", 0),
                            intent.getIntExtra("numOfWrongAnswer", 0));
                    user.setId(id);
                    model.updateUser(user);
                    finish();
                }

            }
        });


    }

}