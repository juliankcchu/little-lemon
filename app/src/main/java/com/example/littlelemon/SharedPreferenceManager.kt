package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences

// https://chintanjoshi1.medium.com/using-sharedpreferences-in-android-jetpack-compose-f8e970ffbf06
class SharedPreferenceManager (context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun clearAll() {
        sharedPreferences.edit().clear().commit()
    }

    fun clearData(key: String) {
        sharedPreferences.edit().remove(key).commit();
    }

    fun saveData(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getData(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

}