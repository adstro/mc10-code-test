package com.mc10inc.codetest.api;

import com.mc10inc.codetest.model.User;

import org.threeten.bp.Instant;

/**
 * TODO
 *
 * @author Adam Stroud &#60;<a href="mailto:adam.stroud@gmail.com">adam.stroud@gmail.com</a>&#62;
 */
public class LoginResponse {
    private final User user;
    private final String accessToken;
    private final Instant expiration;

    public LoginResponse(User user, String accessToken, Instant expiration) {
        this.user = user;
        this.accessToken = accessToken;
        this.expiration = expiration;
    }

    public User getUser() {
        return user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Instant getExpiration() {
        return expiration;
    }
}
