package com.ideologer.zamishoapp.dto.request;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class AbstractJsonObject {
    public class FieldsRequest {
        public static final String PHONE_NUMBER = "phone_number";
        public static final String FIRST_NAME = "first_name";
        public static final String lAST_NAME = "last_name";
        public static final String PROVINCE = "province";
        public static final String CITY = "city";
        public static final String DATE_OF_BIRTH = "dob";
        public static final String GENDER = "gender";
        public static final String ADDRESS = "address";
        public static final String POSTAL_CODE = "postal_code";
        public static final String EMAIL = "email";
    }
    public class FieldsResponse {

        public static final String MESSAGE = "message";
        public static final String USER_TOKEN = "token";
    }
    protected abstract JsonObject getBody();

    protected JSONObject assemblyJSON(final Object classObject) {
        try {
            return new JSONObject(new Gson().toJson(classObject));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected JsonObject assembleGson(final Object classObject) {
        return (JsonObject) new Gson().toJsonTree(classObject);
    }
}
