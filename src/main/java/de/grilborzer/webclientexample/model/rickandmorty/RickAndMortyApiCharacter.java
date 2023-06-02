package de.grilborzer.webclientexample.model.rickandmorty;

public record RickAndMortyApiCharacter(
        int id,
        String name,
        String status,
        String species
) {
}
