package de.grilborzer.webclientexample.service;

import de.grilborzer.webclientexample.model.RickAndMortyApiCharacter;
import de.grilborzer.webclientexample.model.RickAndMortyApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@Service
public class RickAndMortyApiService {

    private final WebClient webClient = WebClient.create("https://rickandmortyapi.com/api");

    public List<RickAndMortyApiCharacter> getAllCharacters() {
        RickAndMortyApiResponse responseEntity = Objects.requireNonNull(webClient.get()
                        .uri("/character")
                        .retrieve()
                        .toEntity(RickAndMortyApiResponse.class)
                        .block())
                .getBody();

        
        assert responseEntity != null;
        return responseEntity.results();
    }
}
