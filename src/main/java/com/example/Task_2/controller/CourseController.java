package com.example.Task_2.controller;

import com.example.Task_2.model.Course;
import com.example.Task_2.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return this.courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public List<Course> getCourseByInstructorId(@PathVariable Integer id) {
        return this.courseService.getCoursesByInstructorId(id);
    }

    @PostMapping("/{courseId}/students/{studentId}")
    public String enrollStudent(@PathVariable Integer courseId, @PathVariable Integer studentId) {
        this.courseService.enrollStudent(courseId, studentId);
        return "Student enrolled in course successfully.";
    }

    @PutMapping("/{courseId}/students/{studentId}")
    public String unEnrollStudent(@PathVariable Integer courseId, @PathVariable Integer studentId) {
        this.courseService.unenrollStudent(courseId, studentId);
        return "Student unenrolled from course successfully.";
    }

}

