package com.chris.institution_management_demo.modules;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@Slf4j
@RequestMapping("institution")
public class InstitutionController {
    @Autowired
    InstitutionService service;

    @PostMapping("/save")
    public ResponseEntity<String> saveInstitution(@RequestBody Institution institution){
        log.info("saving record ....");
        return new ResponseEntity<>(service.saveInst(institution), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Institution>> getAllInstitutions(){
        log.info("getting all records ....");
        return new ResponseEntity<>(service.getAllInst(), HttpStatus.OK);

    }

    @GetMapping("/all/asc")
    public ResponseEntity<List<Institution>> getAllInstitutionsAsc(){
        log.info("getting all records ....");
        return new ResponseEntity<>(service.getAllInstSortedAsc(), HttpStatus.OK);

    }

    @GetMapping("/all/desc")
    public ResponseEntity<List<Institution>> getAllInstitutionsDesc(){
        log.info("getting all records ....");
        return new ResponseEntity<>(service.getAllInstSortedDesc(), HttpStatus.OK);

    }

    @GetMapping("/{instId}")
    public ResponseEntity<Optional<Institution>> getInstitution(@PathVariable int instId){
        log.info("getting record ....");
        return new ResponseEntity<>(service.getInst(instId), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> changeInstitutionName(@RequestParam String newName, @RequestParam String dbName){
        log.info("Changing name ....");
        String feedback = service.editInstName(newName, dbName);
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }

    @GetMapping("/inst")
    public ResponseEntity<Optional<Institution>> getInstitution(@RequestParam String instName){
        log.info("getting institution ....");
        return new ResponseEntity<>(service.getInst(instName), HttpStatus.OK);
    }


    @GetMapping("/courses")
    public ResponseEntity<Set<Course>> getCoursesPerInstitution(@RequestParam String instName){
        log.info("getting courses ....");
        return new ResponseEntity<>(service.getAllCoursesByInst(instName), HttpStatus.OK);
    }

    @GetMapping("/courses/sorted")
    public ResponseEntity<List<Course>> getCoursesPerInstitutionSorted(@RequestParam String instName){
        log.info("getting courses ....");
        return new ResponseEntity<>(service.getAllCoursesByInstSorted(instName), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteInstitution(@RequestParam String instName){
        log.info("deleting institution ....");
        return new ResponseEntity<>(service.deleteInst(instName), HttpStatus.OK);
    }



}
