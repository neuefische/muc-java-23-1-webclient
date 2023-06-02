package de.grilborzer.webclientexample.service;

import de.grilborzer.webclientexample.model.student.Student;
import org.springframework.beans.factory.annotation.Value;
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
        return Objects.requireNonNull(this.webClient.post()
                        .bodyValue(student)
                        .retrieve()
                        .toEntity(Student.class)
                        .block())
                .getBody();
    }

    public List<Student> getAllStudents() {
        return Objects.requireNonNull(webClient.get()
                        .retrieve()
                        .toEntityList(Student.class)
                        .block())
                .getBody();
    }
}
