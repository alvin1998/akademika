package com.AdaInt.akademika.helper

import android.content.Context
import android.content.SharedPreferences

class Sessions(context: Context) {

    private val SESSION_NAME = "sessionad"

    var settings: SharedPreferences
    var context: Context

    fun createSession(key: String?, value: String?) {
        val editor = settings.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getSession(key: String?): String? {
        val settings: SharedPreferences = context.getSharedPreferences(SESSION_NAME, 0)
        return settings.getString(key, "")
    }

    init {
        this.context = context
        settings = context.getSharedPreferences(
            SESSION_NAME,
            0
        ) //we have to define session name here
    }
}