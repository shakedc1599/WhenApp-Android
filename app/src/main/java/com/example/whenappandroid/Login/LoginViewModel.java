package com.example.whenappandroid.Login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.whenappandroid.Data.UserRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    private UserRepository repository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = UserRepository.getInstance(application);
    }

    public void login(String username, String password, Callback<String> callback) {
        repository.login(username, password).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.code() == 200){
                    callback.onResponse(call, response);
                    repository.loadUser(username);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
