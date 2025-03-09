package com.example.Task_2.repository;

import com.example.Task_2.model.Course;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO student_course (student_id, course_id) " +
                   "VALUES(:studentId, :courseId)", nativeQuery = true)
    void enrollStudent(@Param("courseId") Integer courseId, @Param("studentId") Integer studentId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM student_course " +
                   "WHERE student_id = :studentId and course_id = :courseId",
                    nativeQuery = true)
    int unEnrollStudent(@Param("courseId") Integer courseId, @Param("studentId") Integer studentId);

    @Query(value = "SELECT EXISTS(SELECT * FROM student_course WHERE student_id = :studentId AND course_id = :courseId);", nativeQuery = true)
    Boolean studentEnrolledInCourse(@Param("courseId") Integer courseId, @Param("studentId") Integer studentId);

    List<Course> findByInstructorId(Integer id);

}
