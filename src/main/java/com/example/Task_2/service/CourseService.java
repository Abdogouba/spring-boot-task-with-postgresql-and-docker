package com.example.Task_2.service;

import com.example.Task_2.model.Course;
import com.example.Task_2.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private CourseRepository courseRepository;
    private StudentService studentService;
    private InstructorService instructorService;

    @Autowired
    public CourseService(CourseRepository courseRepository, StudentService studentService, InstructorService instructorService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
        this.instructorService = instructorService;
    }

    public List<Course> getAllCourses() {
        return this.courseRepository.findAll();
    }

    public Optional<Course> getCourseByID(Integer id) {
        return this.courseRepository.findById(id);
    }

    public List<Course> getCoursesByInstructorId(Integer instructorId) {
        return this.courseRepository.findByInstructorId(instructorId);
    }

    @Transactional
    public void enrollStudent(Integer courseId, Integer studentId) {
        if (!this.courseRepository.existsById(courseId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course does not exist");

        if (!this.studentService.getStudentById(studentId).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student does not exist");

        if (this.courseRepository.studentEnrolledInCourse(courseId, studentId))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Student already enrolled in course");

        this.courseRepository.enrollStudent(courseId, studentId);
    }

    @Transactional
    public void unenrollStudent(Integer courseId, Integer studentId) {
        int rowsAffected = this.courseRepository.unEnrollStudent(courseId, studentId);

        if (rowsAffected == 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This student is not enrolled in this course");
    }

}
