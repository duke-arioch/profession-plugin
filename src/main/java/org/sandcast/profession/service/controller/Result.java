package org.sandcast.profession.service.controller;

public enum Result {
    FUMBLE(false), FAILURE(false), SUCCESS(true), CRITICAL(true);
    private final boolean successful;

    private Result(boolean successful) {
        this.successful = successful;
    }

    public boolean isSucceessful() {
        return successful;
    }
}
