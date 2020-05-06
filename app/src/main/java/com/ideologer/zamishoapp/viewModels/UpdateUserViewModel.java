package com.ideologer.zamishoapp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;
import com.ideologer.zamishoapp.dto.response.UpdateUserResponse;
import com.ideologer.zamishoapp.network.ApiRestClient;
import com.ideologer.zamishoapp.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUserViewModel extends ViewModel {
    private ApiService apiService;
    private MutableLiveData<UpdateUserResponse> responseMutableLiveData;

    public void inIT(String user_token, JsonObject object) {
        apiService = ApiRestClient.getApiService();
        responseMutableLiveData = new MutableLiveData<>();
        updateUserCall(user_token, object);

    }

    public LiveData<UpdateUserResponse> updateUserLiveData() {
        return responseMutableLiveData;
    }

    private void updateUserCall(String user_token, JsonObject object) {
        apiService.updateUser(user_token, object).enqueue(new Callback<UpdateUserResponse>() {
            @Override
            public void onResponse(Call<UpdateUserResponse> call, Response<UpdateUserResponse> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    responseMutableLiveData.postValue(response.body());
                }else {
                    responseMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<UpdateUserResponse> call, Throwable t) {
                responseMutableLiveData.postValue(null);
            }
        });
    }
}
