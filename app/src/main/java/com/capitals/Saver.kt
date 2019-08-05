package com.capitals


import android.content.Context
import android.content.SharedPreferences


class Saver(private val context: Context) {

    private val shared = context.getSharedPreferences("NAME", Context.MODE_PRIVATE)

    fun saveText(text: String) {
        shared.edit().putString("KEY", text).apply()
    }

    fun getText(): String {
        return shared.getString("KEY", "") ?: ""
    }

}