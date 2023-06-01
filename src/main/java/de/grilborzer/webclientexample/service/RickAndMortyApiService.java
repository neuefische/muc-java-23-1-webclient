package de.grilborzer.webclientexample.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RickAndMortyApiService {

    private final WebClient webClient = WebClient.create("https://rickandmortyapi.com/api");

    public String getAllCharacters() {
        String responseEntity = webClient.get()
                .uri("/character")
                .retrieve()
                .toEntity(String.class)
                .block()
                .getBody();

        return String.valueOf(responseEntity);
    }
}
