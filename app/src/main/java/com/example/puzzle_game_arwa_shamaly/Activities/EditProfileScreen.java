package com.example.puzzle_game_arwa_shamaly.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import com.example.puzzle_game_arwa_shamaly.databinding.ActivityEditProfileScreenBinding;

import java.util.Calendar;

public class EditProfileScreen extends AppCompatActivity {
ActivityEditProfileScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEditProfileScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvSelectBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //جلب التاريخ الحالي و جعله الافتراضي
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                if (year < now.get(Calendar.YEAR)){
                                    binding.tvSelectBirthdate.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                                    String date = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
                                }else{
                                    Toast.makeText(EditProfileScreen.this, "error date", Toast.LENGTH_SHORT).show();
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
        //valed email



    }

}