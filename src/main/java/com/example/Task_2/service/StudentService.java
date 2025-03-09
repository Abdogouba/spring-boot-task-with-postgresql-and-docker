package com.example.Task_2.service;

import com.example.Task_2.model.Student;
import com.example.Task_2.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Integer id) {
        return this.studentRepository.findById(id);
    }

    @Transactional
    public Student saveStudent(Student student) {
        student.setId(null);

        if (student.getName() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is required");

        if (student.getEmail() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is required");

        if (this.studentRepository.findByEmail(student.getEmail()).isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");

        return this.studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent(Student student) {
        if (student.getId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id is required");

        if (!this.studentRepository.existsById(student.getId()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");

        if (student.getName() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is required");

        if (student.getEmail() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is required");

        if (this.studentRepository.findByEmail(student.getEmail()).isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");

        return this.studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Integer id) {
        if (!this.studentRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");

        this.studentRepository.deleteById(id);
    }

    public List<Student> getStudentsByCourseId(Integer courseId) {
        return this.studentRepository.findStudentsByCourseId(courseId);
    }

}
