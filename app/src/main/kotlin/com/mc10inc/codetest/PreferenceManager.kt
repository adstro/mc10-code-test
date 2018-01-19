package com.mc10inc.codetest

import android.content.Context
import org.threeten.bp.Instant
import java.util.*

/**
 * TODO
 *
 * @author Adam Stroud &#60;[adam.stroud@gmail.com](mailto:adam.stroud@gmail.com)&#62;
 */
class PreferenceManager(context: Context) {
    private val sharedPreferences = android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    var accessToken
        get() = getString(KEY_ACCESS_TOKEN)
        set(accessToken) = sharedPreferences.edit()
                .putString(KEY_ACCESS_TOKEN, accessToken)
                .apply()

    var accessTokenExpiration
        get() = Instant.ofEpochMilli(getLong(KEY_ACCESS_TOKEN_EXPIRATION) ?: 0)
        set(accessTokenExpiration) = sharedPreferences.edit()
                .putLong(KEY_ACCESS_TOKEN_EXPIRATION, accessTokenExpiration.toEpochMilli())
                .apply()

    var userId
        get() = UUID.fromString(getString(KEY_USER_ID))
        set(userId) = sharedPreferences.edit()
                .putString(KEY_USER_ID, userId.toString())
                .apply()

    var accountId
        get() = UUID.fromString(getString(KEY_ACCOUNT_ID))
        set(accountId) = sharedPreferences.edit()
                .putString(KEY_ACCOUNT_ID, accountId.toString())
                .apply()

    fun clear() = sharedPreferences.edit().clear().apply()

    private fun getLong(key: String): Long? {
        val value = sharedPreferences.getLong(key, -1)

        return if (value == -1L) null else value
    }

    private fun getString(key: String): String? = sharedPreferences.getString(key, null)

    companion object {
        private const val KEY_ACCESS_TOKEN = "accessToken"
        private const val KEY_ACCESS_TOKEN_EXPIRATION = "accessTokenExpiration"
        private const val KEY_ACCOUNT_ID = "accountId"
        private const val KEY_USER_ID = "userId"
    }
}