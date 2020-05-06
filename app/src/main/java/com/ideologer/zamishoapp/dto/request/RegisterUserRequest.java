package com.ideologer.zamishoapp.dto.request;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class RegisterUserRequest extends AbstractJsonObject {
    @SerializedName(FieldsRequest.PHONE_NUMBER)
    private String phoneNumber;

    public RegisterUserRequest(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Override
    public JsonObject getBody() {
        return assembleGson(this);
    }
}
