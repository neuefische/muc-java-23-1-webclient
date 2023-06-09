package de.grilborzer.webclientexample.controller;

import de.grilborzer.webclientexample.model.rickandmorty.RickAndMortyApiCharacter;
import de.grilborzer.webclientexample.service.RickAndMortyApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rickandmorty")
public class RickAndMortyApiController {

    private final RickAndMortyApiService rickAndMortyApiService;

    public RickAndMortyApiController(RickAndMortyApiService rickAndMortyApiService) {
        this.rickAndMortyApiService = rickAndMortyApiService;
    }

    @GetMapping("/characters")
    public List<RickAndMortyApiCharacter> getAllCharacters() {
        return rickAndMortyApiService.getAllCharacters();
    }

    @GetMapping("/characters/random")
    public RickAndMortyApiCharacter getRandomCharacter() {
        return rickAndMortyApiService.getRandomCharacter();
    }

    @GetMapping("/characters/alive")
    public List<RickAndMortyApiCharacter> getAllCharactersAlive() {
        return rickAndMortyApiService.getAllCharactersAlive();
    }

    @GetMapping("/speciesstatistic/{species}")
    public int getSpeciesStatistic(@PathVariable String species) {
        return rickAndMortyApiService.getSpeciesStatistic(species);
    }
}
