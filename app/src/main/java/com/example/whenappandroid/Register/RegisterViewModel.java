package com.example.whenappandroid.Register;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.whenappandroid.Data.UserRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends AndroidViewModel {
    private UserRepository repository;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        repository = UserRepository.getInstance(application);
    }

    public void register(String username, String password, Callback<String> callback) {
        repository.register(username, password).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onResponse(call, response);
                repository.loadUser(username);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

}