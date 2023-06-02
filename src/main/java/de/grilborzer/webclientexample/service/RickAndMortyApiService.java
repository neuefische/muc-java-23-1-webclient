package de.grilborzer.webclientexample.service;

import de.grilborzer.webclientexample.model.rickandmorty.CharacterStatus;
import de.grilborzer.webclientexample.model.rickandmorty.RickAndMortyApiCharacter;
import de.grilborzer.webclientexample.model.rickandmorty.RickAndMortyApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class RickAndMortyApiService {

    private final WebClient webClient;

    public RickAndMortyApiService(
            @Value("${rickandmorty.url}")
            String webclientUrl) {
        webClient = WebClient.create(webclientUrl);
    }


    public List<RickAndMortyApiCharacter> getAllCharacters() {
        RickAndMortyApiResponse responseEntity = webClient.get()
                .uri("/character")
                .retrieve()
                .toEntity(RickAndMortyApiResponse.class)
                .block()
                .getBody();

        Objects.requireNonNull(responseEntity);
        return responseEntity.results();
    }

    public List<RickAndMortyApiCharacter> getAllCharactersAlive() {
        List<RickAndMortyApiCharacter> allCharacters = getAllCharacters();
        List<RickAndMortyApiCharacter> charactersAlive = new ArrayList<>();

        for (RickAndMortyApiCharacter character : allCharacters) {
            if (character.status().equals(CharacterStatus.ALIVE.status())) {
                charactersAlive.add(character);
            }
        }

        return charactersAlive;
    }

    public RickAndMortyApiCharacter getRandomCharacter() {
        List<RickAndMortyApiCharacter> allCharacters = getAllCharacters();

        return getRandomElementFromList(allCharacters);
    }

    private static RickAndMortyApiCharacter getRandomElementFromList(List<RickAndMortyApiCharacter> characterList) {
        // Create a new Random object
        Random random = new Random();

        // Generate a random index within the size of the list
        int randomIndex = random.nextInt(characterList.size());

        // Return the element at the random index
        return characterList.get(randomIndex);
    }
}
