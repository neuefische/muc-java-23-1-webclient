package de.grilborzer.webclientexample.model;

import java.util.List;

public record RickAndMortyApiResponse(
        Object info,
        List<RickAndMortyApiCharacter> results
) {
}
