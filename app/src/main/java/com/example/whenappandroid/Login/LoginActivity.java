package com.example.whenappandroid.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.whenappandroid.ChatScreen.Vertical.VerticalContactsActivity;
import com.example.whenappandroid.Data.Globals;
import com.example.whenappandroid.R;
import com.example.whenappandroid.Register.RegisterActivity;
import com.example.whenappandroid.SettingsScreen.SettingsActivity;
import com.example.whenappandroid.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.signinBtn.setOnClickListener(v -> {
            Intent i = new Intent(this, RegisterActivity.class);
            startActivity(i);
        });

        binding.loginBtn.setOnClickListener(v -> {
            String username = binding.usernameInputBox.getText().toString();
            String password = binding.passwordInputBox.getText().toString();

            viewModel.login(username, password, new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.code() == 400) {
                        binding.ErrorText.setText("Combination is incorrect");
                        return;
                    }

                    if (response.code() == 200) {
                        Globals.currentUser = username;
                        Intent intent = new Intent(LoginActivity.this, VerticalContactsActivity.class);
                        startActivity(intent);
                        return;
                    }

                    binding.ErrorText.setText("Error connecting to server");
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    binding.ErrorText.setText("Error connecting to server");
                }
            });
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

}