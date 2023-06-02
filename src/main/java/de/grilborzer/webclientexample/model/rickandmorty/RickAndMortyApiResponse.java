package de.grilborzer.webclientexample.model.rickandmorty;

import java.util.List;

public record RickAndMortyApiResponse(
        Object info,
        List<RickAndMortyApiCharacter> results
) {
}
