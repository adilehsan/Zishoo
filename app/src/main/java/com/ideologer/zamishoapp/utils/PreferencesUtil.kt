package com.ideologer.zamishoapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.ideologer.zamishoapp.Application

class PreferencesUtil{
    companion object{
        fun getFloatValue(key: String, defaultValue: Float): Float {
            return getSharedPreferences().getFloat(key, defaultValue)
        }

        fun setFloatValue(key: String, value: Float) {
            getSharedPreferences().edit().putFloat(key, value).apply()
        }

        fun getLongValue(key: String, defaultValue: Long): Long {
            return getSharedPreferences().getLong(key, defaultValue)
        }

        fun setLongValue(key: String, value: Long) {
            getSharedPreferences().edit().putLong(key, value).apply()
        }

        fun setIntValue(key: String, value: Int) {
            getSharedPreferences().edit().putInt(key, value).apply()
        }


        fun getIntValue(key: String, defaultValue: Int): Int {
            return getSharedPreferences().getInt(key, defaultValue)
        }

        fun setStringValue(key: String, value: String) {
            getSharedPreferences().edit().putString(key, value).apply()
        }

        fun getStringValue(key: String): String? {
            return getSharedPreferences().getString(key,"")
        }

        fun getStringValue(key: String, defValue: String): String? {
            return getSharedPreferences().getString(key, defValue)
        }

        fun getBooleanValue(key: String, defaultValue: Boolean): Boolean {
            return getSharedPreferences().getBoolean(key, defaultValue)
        }

        fun setBooleanValue(key: String, value: Boolean) {
            getSharedPreferences().edit().putBoolean(key, value).apply()
        }

        fun clearPreferences() {
            getSharedPreferences().edit().clear().apply()
        }

        fun clearPreference(key: String) {
            getSharedPreferences().edit().remove(key).apply()
        }

        private fun getSharedPreferences(): SharedPreferences {
            return Application.getInstance().getSharedPreferences("zamisho_preferences", Context.MODE_PRIVATE)
        }
    }
}
