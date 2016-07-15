package com.epicodus.checkup.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.checkup.Constants;
import com.epicodus.checkup.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FindDoctorActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Bind(R.id.specialtySearchButton) Button mSpecialtySearchButton;
    @Bind(R.id.specialtyEditText) EditText mSpecialtyEditText;
    @Bind(R.id.cityEditText) TextView mCityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);
        ButterKnife.bind(this);
        mSpecialtySearchButton.setOnClickListener(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
    }

    @Override
    public void onClick(View v) {
        if(v == mSpecialtySearchButton) {
            String specialty = mSpecialtyEditText.getText().toString();
            String location = mCityEditText.getText().toString();

            if(!(specialty).equals("")) {
                addToSharedPreferences(specialty);
            }

            Intent intent = new Intent(FindDoctorActivity.this, DoctorListActivity.class);
            intent.putExtra("specialty", specialty);
            intent.putExtra("location", location);

            startActivity(intent);
        }
    }

    private void addToSharedPreferences(String specialty) {
        mEditor.putString(Constants.PREFERENCES_SPECIALTY_KEY, specialty).apply();
    }
}
