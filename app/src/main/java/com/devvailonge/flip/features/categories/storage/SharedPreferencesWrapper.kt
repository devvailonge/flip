package com.devvailonge.flip.features.categories.storage

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesWrapper private constructor(
    private val preference: SharedPreferences
) {

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return preference.getBoolean(key, defValue)
    }

    fun setBoolean(key: String, value: Boolean) {
        preference
            .edit()
            .putBoolean(key, value)
            .apply()
    }

    companion object {

        private const val IDENTIFIER = "com.devvailonge.flip.IDENTIFIER"

        const val KEY_SHOW_ONBOARDING = "KEY_SHOW_ONBOARD"

        fun create(context: Context): SharedPreferencesWrapper {
            val sharedPreferences = context.getSharedPreferences(IDENTIFIER, Context.MODE_PRIVATE)
            return SharedPreferencesWrapper(sharedPreferences)
        }
    }
}