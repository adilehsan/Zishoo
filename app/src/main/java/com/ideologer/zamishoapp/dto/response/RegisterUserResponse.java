package com.ideologer.zamishoapp.dto.response;

import com.google.gson.annotations.SerializedName;
import com.ideologer.zamishoapp.dto.request.AbstractJsonObject;

public class RegisterUserResponse extends BaseResponse {

    @SerializedName(AbstractJsonObject.FieldsResponse.USER_TOKEN)
    private String userToken;

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
