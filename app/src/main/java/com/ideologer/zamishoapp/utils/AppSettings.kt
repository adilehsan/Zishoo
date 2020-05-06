package com.ideologer.zamishoapp.utils

class AppSettings {
    companion object{
        private var USER_NUMBER= "user_number"
        private var USER_TOKEN = "user_token"
        private var USER_LOGIN = "user_login"

        fun setUserNumber(phoneNumber: String){
            PreferencesUtil.setStringValue(USER_NUMBER,phoneNumber)
        }
        fun getUserNumber(): String?{
            return PreferencesUtil.getStringValue(USER_NUMBER)
        }

        fun setUserToken(token: String){
            PreferencesUtil.setStringValue(USER_TOKEN,token)
        }
        fun getUserToken(): String?{
            return PreferencesUtil.getStringValue(USER_TOKEN)
        }

        fun setUserLogin(token: Boolean){
            PreferencesUtil.setBooleanValue(USER_LOGIN,token)
        }
        fun getUserLogin(): Boolean{
            return PreferencesUtil.getBooleanValue(USER_LOGIN,false)
        }
    }



}