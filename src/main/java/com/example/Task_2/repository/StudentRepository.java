package com.example.Task_2.repository;

import com.example.Task_2.model.Course;
import com.example.Task_2.model.Instructor;
import com.example.Task_2.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "SELECT s.* " +
                   "FROM student s INNER JOIN student_course sc ON s.id = sc.student_id " +
                   "WHERE sc.course_id = :courseId",
                    nativeQuery = true)
    List<Student> findStudentsByCourseId(@Param("courseId") Integer courseId);

    Optional<Student> findByEmail(String email);
}
