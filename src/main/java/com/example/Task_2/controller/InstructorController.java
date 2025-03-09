package com.example.Task_2.controller;

import com.example.Task_2.model.Instructor;
import com.example.Task_2.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    private InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public List<Instructor> getAllInstructors() {
        return this.instructorService.getAllInstructors();
    }

    @GetMapping("/{email}")
    public Instructor getInstructorByEmail(@PathVariable String email) {
        return this.instructorService.getInstructorByEmail(email);
    }

}
