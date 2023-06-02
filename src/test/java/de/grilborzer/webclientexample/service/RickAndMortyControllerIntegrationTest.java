package de.grilborzer.webclientexample.service;

import de.grilborzer.webclientexample.model.rickandmorty.RickAndMortyApiCharacter;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

import static org.springframework.http.ResponseEntity.status;

@AutoConfigureMockMvc
@SpringBootTest
class RickAndMortyControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    static MockWebServer mockWebServer;

    @BeforeAll
    static void beforeAll() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @DynamicPropertySource
    static void backendProperties(DynamicPropertyRegistry registry) {
        registry.add("rickandmorty.url", () -> mockWebServer.url("/").toString());
    }

    @Test
    void getAllCharacters_whenApiReturnsTwoEntries_thenResultingListShouldContainTwoCharacters() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setHeader("Content-Type", "application/json")
                .setBody(
                        """
                                   {
                                   "results": [
                                    {
                                        "id": 1,
                                        "name": "Rick Sanchez",
                                        "status": "Alive",
                                        "species": "Human"
                                    },
                                    {
                                        "id": 2,
                                        "name": "Morty Smith",
                                        "status": "Alive",
                                        "species": "Human"
                                    }
                                    ]        }         
                                """
                ));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/rickandmorty/characters"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        [
                            {
                                "id": 1,
                                "name": "Rick Sanchez",
                                "status": "Alive",
                                "species": "Human"
                            },
                            {
                                "id": 2,
                                "name": "Morty Smith",
                                "status": "Alive",
                                "species": "Human"
                            }
                            ]
                                                """));
    }

    @Test
    void getAllCharactersAlive_whenApiReturnsThreeEntriesWithSingleLivingCharacter_thenResultingListShouldContainSingleCharacters() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setHeader("Content-Type", "application/json")
                .setBody(
                        """
                                   {
                                   "results": [
                                    {
                                        "id": 1,
                                        "name": "Roboter Rick",
                                        "status": "Dead",
                                        "species": "Human"
                                    },
                                    {
                                        "id": 2,
                                        "name": "Morty Smith",
                                        "status": "Alive",
                                        "species": "Human"
                                    },
                                    {
                                        "id": 3,
                                        "name": "Roboter Morty",
                                        "status": "Dead",
                                        "species": "Human"
                                    }
                                    ]        }         
                                """
                ));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/rickandmorty/characters/alive"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        [
                            {
                                "id": 2,
                                "name": "Morty Smith",
                                "status": "Alive",
                                "species": "Human"
                            }
                            ]
                                                """));
    }

    @AfterAll
    static void afterAll() throws IOException {
        mockWebServer.close();
    }
}