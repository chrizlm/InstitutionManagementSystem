package com.chris.institution_management_demo.modules;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* Course controller has the end points for courses
* save a course
* get all courses - in ascending and descending orders
* get a single course through id
* edit name of course and delete the course
*
* */

@Controller
@Slf4j
@RequestMapping("courses")
public class CourseController {
    @Autowired
    CourseService service;

    @PostMapping("/save")
    public ResponseEntity<String> saveCourse(@RequestBody Course course){
        log.info("request to save course");
        return new ResponseEntity<>(service.saveCourse(course), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourses(){
        log.info("request to get all records");
        return new ResponseEntity<>(service.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/all/asc")
    public ResponseEntity<List<Course>> getAllCoursesAsc(){
        log.info("request to get all records");
        return new ResponseEntity<>(service.getAllCoursesAsc(), HttpStatus.OK);
    }

    @GetMapping("/all/desc")
    public ResponseEntity<List<Course>> getAllCoursesDesc(){
        log.info("request to get all records");
        return new ResponseEntity<>(service.getAllCoursesDesc(), HttpStatus.OK);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable int courseId){
        log.info("request to get a record");
        return new ResponseEntity<>(service.getCourse(courseId), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> editCourse(@RequestParam String newName, @RequestParam String dbName){
        log.info("request to edit course name of a record");
        return new ResponseEntity<>(service.editCourse(newName, dbName), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCourse(@RequestParam String courseName){
        log.info("request to delete course");
        return new ResponseEntity<>(service.deleteCourse(courseName), HttpStatus.OK);
    }
}
