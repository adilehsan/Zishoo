package com.ideologer.zamishoapp.dto.request;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class UpdateUserRequest extends AbstractJsonObject {

    @SerializedName(FieldsRequest.PHONE_NUMBER)
    private String phoneNumber;

    @SerializedName(FieldsRequest.FIRST_NAME)
    private String firstName;

    @SerializedName(FieldsRequest.lAST_NAME)
    private String lastName;

    @SerializedName(FieldsRequest.CITY)
    private String city;

    @SerializedName(FieldsRequest.PROVINCE)
    private String province;

    @SerializedName(FieldsRequest.POSTAL_CODE)
    private String postalCode;

    @SerializedName(FieldsRequest.EMAIL)
    private String email;

    @SerializedName(FieldsRequest.GENDER)
    private String gender;

    @SerializedName(FieldsRequest.DATE_OF_BIRTH)
    private String dateOfBirth;

    @SerializedName(FieldsRequest.ADDRESS)
    private String address;


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UpdateUserRequest(final String phoneNumber,final String firstName,final String lastName,final String city,final String province,final String address,final String postalCode,final String email,final String gender,final String dateOfBirth) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.province = province;
        this.address = address;
        this.postalCode = postalCode;
        this.email = email;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public JsonObject getBody() {
        return assembleGson(this);
    }
}
