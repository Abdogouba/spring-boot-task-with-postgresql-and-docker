package com.example.Task_2.service;

import com.example.Task_2.model.Instructor;
import com.example.Task_2.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    private InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<Instructor> getAllInstructors() {
        return this.instructorRepository.findAll();
    }

    public Optional<Instructor> getInstructorByID(Integer id) {
        return this.instructorRepository.findById(id);
    }

    public Instructor getInstructorByEmail(String email) {
        if (email == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is required");

        Optional<Instructor> instructor = this.instructorRepository.findInstructorByEmail(email);

        if (instructor.isPresent())
            return instructor.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such instructor");
    }

}
