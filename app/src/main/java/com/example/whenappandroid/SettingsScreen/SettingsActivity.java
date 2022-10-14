package com.example.whenappandroid.SettingsScreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.whenappandroid.R;
import com.example.whenappandroid.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {

    private ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
//            setTheme(R.style.Theme_Dark);
            binding.themeSwitch.setChecked(true);
        } else {
//            setTheme(R.style.Theme_Light);
            binding.themeSwitch.setChecked(false);
        }

        binding.submitBtn.setOnClickListener(v -> {
            SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("server", binding.serverInputBox.getText().toString());
            editor.apply();
        });

        binding.themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

//            SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putBoolean("dark", isChecked);
//            editor.apply();
        });
    }
}