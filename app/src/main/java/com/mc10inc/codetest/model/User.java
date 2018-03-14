package com.mc10inc.codetest.model;

import java.util.TimeZone;
import java.util.UUID;

/**
 * TODO
 *
 * @author Adam Stroud &#60;<a href="mailto:adam.stroud@gmail.com">adam.stroud@gmail.com</a>&#62;
 */
public class User {
    private final UUID id;
    private final UUID accountId;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String locale;
    private final TimeZone timeZone;
    private final int legaleseVersionRequired;
    private final int legaleseVersionAccepted;

    public User(UUID id, UUID accountId, String email, String firstName, String lastName, String locale, TimeZone timeZone, int legaleseVersionRequired, int legaleseVersionAccepted) {
        this.id = id;
        this.accountId = accountId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.locale = locale;
        this.timeZone = timeZone;
        this.legaleseVersionRequired = legaleseVersionRequired;
        this.legaleseVersionAccepted = legaleseVersionAccepted;
    }

    public UUID getId() {
        return id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLocale() {
        return locale;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public int getLegaleseVersionRequired() {
        return legaleseVersionRequired;
    }

    public int getLegaleseVersionAccepted() {
        return legaleseVersionAccepted;
    }
}
