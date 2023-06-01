package de.grilborzer.webclientexample.controller;

import de.grilborzer.webclientexample.model.RickAndMortyApiCharacter;
import de.grilborzer.webclientexample.service.RickAndMortyApiService;
import org.springframework.web.bind.annotation.GetMapping;
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
}
