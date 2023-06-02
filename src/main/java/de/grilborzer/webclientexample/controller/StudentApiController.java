package de.grilborzer.webclientexample.controller;

import de.grilborzer.webclientexample.model.student.Student;
import de.grilborzer.webclientexample.service.StudentApiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentApiController {

    private final StudentApiService studentApiService;

    public StudentApiController(StudentApiService studentApiService) {
        this.studentApiService = studentApiService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentApiService.getAllStudents();
    }


    @PostMapping
    public Student sendPost(@RequestBody Student student) {
        return studentApiService.sendPost(student);
    }
}
