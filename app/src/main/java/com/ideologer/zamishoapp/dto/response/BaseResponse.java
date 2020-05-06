package com.ideologer.zamishoapp.dto.response;

import com.google.gson.annotations.SerializedName;
import com.ideologer.zamishoapp.dto.request.AbstractJsonObject;

import java.io.Serializable;

public class BaseResponse implements Serializable {

    @SerializedName(AbstractJsonObject.FieldsResponse.MESSAGE)
    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
