package com.mc10inc.codetest.model;

import org.threeten.bp.Instant;

import java.util.UUID;

/**
 * TODO
 *
 * @author Adam Stroud &#60;<a href="mailto:adam.stroud@gmail.com">adam.stroud@gmail.com</a>&#62;
 */
public class Study {
    private final UUID id;
    private final String displayName;
    private final String title;
    private final Instant createdTs;

    public Study(UUID id, String displayName, String title, Instant createdTs) {
        this.id = id;
        this.displayName = displayName;
        this.title = title;
        this.createdTs = createdTs;
    }

    public UUID getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getTitle() {
        return title;
    }

    public Instant getCreatedTs() {
        return createdTs;
    }
}
