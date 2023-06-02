package de.grilborzer.webclientexample.service;

import de.grilborzer.webclientexample.model.student.Student;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@Service
public class StudentApiService {

    private final WebClient webClient = WebClient.create("http://localhost:8080/api/students");

    public Student sendPost(Student student) {
        return Objects.requireNonNull(this.webClient.post()
                        .bodyValue(student)
                        .retrieve()
//                .bodyToMono(Student.class) // Auch möglich
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
