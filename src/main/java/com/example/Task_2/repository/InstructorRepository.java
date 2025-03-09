package com.example.Task_2.repository;

import com.example.Task_2.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

    Optional<Instructor> findInstructorByEmail(String email);

}
