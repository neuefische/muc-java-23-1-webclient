package de.grilborzer.webclientexample.service;

import de.grilborzer.webclientexample.model.student.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@Service
public class StudentApiService {

    private final WebClient webClient;

    public StudentApiService(
            @Value("${studentserver.url.local}")
            String webclientUrl) {
        webClient = WebClient.create(webclientUrl);
    }


    public Student sendPost(Student student) {
        Student studentResponse = this.webClient
                .post()
                // Request-Body (den wir verschicken)
                .bodyValue(student)
                .retrieve()
                // Response-Body (den wir erhalten)
                .toEntity(Student.class)
                .block()
                .getBody();

        Objects.requireNonNull(studentResponse);
        return studentResponse;
    }


    public List<Student> getAllStudents() {
        /*
          Struktur:
        * webclient
        *   .get() -> Das Verb: GET, POST, PUT, DELETE Request?
            .uri("/unterpfad) -> Unterpfad der URL
            .retrieve() -> Die Antwort des Servers abrufen (sie ist (meist) im JSON Format)
            .toEntityList(Student.class) -> Das JSON in Objekte der Zielklasse umwandeln
            .block() -> Warten, bis die Antwort des Servers da ist

            ^Hier haben wir eine ResponseEntity
            (ResponseEntity = Antwort des Servers mit Statuscode, Header und Body)
            .getBody() -> Den Body der ResponseEntity abrufen
        *
        * */

        // Wenn man NUR den Body der Response braucht, kann man mit getBody direkt abruft
        List<Student> studentList = webClient.get()
                // z.Dt. abrufen = ruft die Antwort des GET Requests ab
                .retrieve()
                // toEntityList() wandelt die JSON Antwort in eine Liste von Studenten um
                .toEntityList(Student.class)
                // block() wartet auf die Antwort des GET Requests
                .block()
                // Aus dem Body der Antwort wird die Liste von Studenten extrahiert
                .getBody();

        // requireNonNull prüft, ob die Liste von Studenten nicht null ist (d.h. alle Objekte existieren)
        return Objects.requireNonNull(studentList);
    }
}
