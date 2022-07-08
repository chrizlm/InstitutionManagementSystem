package com.chris.institution_management_demo.modules;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentService service;

    @PostMapping("/save")
    public ResponseEntity<String> saveStudent(@RequestBody Student student){
        log.info("request to save student");
        return new ResponseEntity<>(service.saveStudent(student), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents(){
        log.info("request to get all students");
        return new ResponseEntity<>(service.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/all/institution")
    public ResponseEntity<List<Student>> getAllStudents(@RequestParam String instName){
        log.info("request to get all students");
        return new ResponseEntity<>(service.getAllStudentsByInstitution(instName), HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable int studentId){
        log.info("request to get student");
        return new ResponseEntity<>(service.getStudent(studentId), HttpStatus.OK);
    }

    @PutMapping("/edit/name")
    public ResponseEntity<String> editStudentName(@RequestParam String newName, @RequestParam String dbName){
        log.info("request to change student's name");
        return new ResponseEntity<>(service.editName(newName, dbName), HttpStatus.OK);
    }

    @PutMapping("/transfer")
    public ResponseEntity<String> transferStudent(@RequestParam int studentId, @RequestParam String instName, @RequestParam String courseName){
        log.info("request to transfer student");
        return new ResponseEntity<>(service.transferStudent(studentId, instName, courseName), HttpStatus.OK);
    }

    @PutMapping("/edit/course")
    public ResponseEntity<String> editStudentCourse(@RequestParam String courseName, @RequestParam String dbName){
        log.info("request to change student's course");
        return new ResponseEntity<>(service.editCourse(courseName, dbName), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable int studentId){
        log.info("request to delete student");
        return new ResponseEntity<>(service.deleteStudent(studentId), HttpStatus.OK);
    }
}
