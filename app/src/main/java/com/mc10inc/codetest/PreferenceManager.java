package com.mc10inc.codetest;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import org.threeten.bp.Instant;

import java.util.UUID;

/**
 * TODO
 *
 * @author Adam Stroud &#60;<a href="mailto:adam.stroud@gmail.com">adam.stroud@gmail.com</a>&#62;
 */
public class PreferenceManager {
    private static final String KEY_ACCESS_TOKEN = "accessToken";
    private static final String KEY_ACCESS_TOKEN_EXPIRATION = "accessTokenExpiration";
    private static final String KEY_ACCOUNT_ID = "accountId";
    private static final String KEY_USER_ID = "userId";

    private final SharedPreferences sharedPreferences;

    public PreferenceManager(Context context) {
        this.sharedPreferences = android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());

    }

    public String getAccessToken() {
        return getString(KEY_ACCESS_TOKEN);
    }

    @Nullable
    public void setAccessToken(String accessToken) {
        sharedPreferences.edit().putString(KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Nullable
    public Instant getAccessTokenExpiration() {
        long epochMilli = sharedPreferences.getLong(KEY_ACCESS_TOKEN_EXPIRATION, -1);

        return epochMilli == -1
                ? null :
                Instant.ofEpochMilli(epochMilli);
    }

    public void setAccessTokenExpiration(Instant accessTokenExpiration) {
        sharedPreferences.edit().putLong(KEY_ACCESS_TOKEN_EXPIRATION, accessTokenExpiration.toEpochMilli()).apply();
    }

    public UUID getUserId() {
        return UUID.fromString(getString(KEY_USER_ID));
    }

    @Nullable
    public void setUserId(UUID userId) {
        sharedPreferences.edit().putString(KEY_USER_ID, userId.toString()).apply();
    }

    public UUID getAccountId() {
        return UUID.fromString(getString(KEY_ACCOUNT_ID));
    }

    @Nullable
    public void setAccountId(UUID accountId) {
        sharedPreferences.edit().putString(KEY_ACCOUNT_ID, accountId.toString()).apply();
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }

    @Nullable
    private String getString(String key) {
        return sharedPreferences.getString(key, null);
    }
}
