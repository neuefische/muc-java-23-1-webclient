package de.grilborzer.webclientexample.model;

public record RickAndMortyApiCharacter(
        int id,
        String name,
        String status,
        String species
) {
}
