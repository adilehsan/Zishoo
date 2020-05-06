package com.ideologer.zamishoapp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;
import com.ideologer.zamishoapp.dto.response.RegisterUserResponse;
import com.ideologer.zamishoapp.network.ApiRestClient;
import com.ideologer.zamishoapp.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterUserViewModel extends ViewModel {
    private ApiService apiService;
    private MutableLiveData<RegisterUserResponse> responseMutableLiveData;

    public void inIT(JsonObject object){
        apiService = ApiRestClient.getApiService();
        responseMutableLiveData = new MutableLiveData<>();
        registerUserCall(object);

    }

    public LiveData<RegisterUserResponse> registerUserLiveData(){
        return responseMutableLiveData;
    }
    private void registerUserCall(JsonObject object){
        apiService.getRegisterUser(object).enqueue(new Callback<RegisterUserResponse>() {
            @Override
            public void onResponse(Call<RegisterUserResponse> call, Response<RegisterUserResponse> response) {
                if (response.isSuccessful() && response.code() == 200){
                  responseMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RegisterUserResponse> call, Throwable t) {
                responseMutableLiveData.postValue(null);
            }
        });
    }
}
