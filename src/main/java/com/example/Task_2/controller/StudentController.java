package com.example.Task_2.controller;

import com.example.Task_2.model.Course;
import com.example.Task_2.model.Student;
import com.example.Task_2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return this.studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        Optional<Student> student = this.studentService.getStudentById(id);

        if (student.isPresent())
            return student.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return this.studentService.saveStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        student.setId(id);
        return this.studentService.updateStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Integer id) {
        this.studentService.deleteStudent(id);
    }

    @GetMapping("/course/{id}")
    public List<Student> getStudentsByCourseId(@PathVariable Integer id) {
        return this.studentService.getStudentsByCourseId(id);
    }

}
