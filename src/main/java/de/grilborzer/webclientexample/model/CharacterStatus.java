package de.grilborzer.webclientexample.model;


public enum CharacterStatus {

    ALIVE("Alive"),
    DEAD("Alive"),
    UNKNOWN("Unknown");

    private final String status;

    CharacterStatus(String status) {
        this.status = status;
    }

    public String status() {
        return status;
    }
}
