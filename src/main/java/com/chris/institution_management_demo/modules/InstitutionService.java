package com.chris.institution_management_demo.modules;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class InstitutionService {
    @Autowired
    InstitutionRepo repo;

    @Autowired
    CourseRepo courseRepo;

    public String saveInst(Institution institution){


        Institution inst = repo.findInstitutionByInstName(institution.getInstName()).orElse(new Institution("empty"));
        String db_name = inst.getInstName().toUpperCase();
        String search_name = institution.getInstName().toUpperCase();

        if(db_name.equals(search_name) && db_name != null){
            log.info("there exists an institution with such a name");
            return "Name already exists";
        }else{
            repo.save(institution);
            log.info("saving record ....");
            log.info("record of {} saved!", institution.getInstName());

            return "Institution saved";
        }


    }


    public String addCourseToInstitution(String courseName, String instName){
        log.info("adding course to an institution");
        Course course = courseRepo.findCourseByCourseName(courseName).orElseThrow();
        Institution institution = repo.findInstitutionByInstName(instName).orElseThrow();

        institution.getCourses().add(course);
        return "course added to institution";
    }

    public List<Institution> getAllInst(){
        log.info("finding all records from institution DB");
        return repo.findAll();
    }


    public Set<Course> getAllCoursesByInst(String instName){
        log.info("getting set of courses offered in an institution");
        Institution institution = getInst(instName).orElseThrow();
        return institution.getCourses();
    }

    public List<Course> getAllCoursesByInstSorted(String instName){
        log.info("getting set of courses offered in an institution");
        Institution institution = getInst(instName).orElseThrow();
        Set<Course> courseSet =  institution.getCourses();

        log.info("sorting the list");
        List<Course> courseList = new ArrayList<>(courseSet);
        return (List<Course>) courseList.stream().sorted();
    }

    public List<Institution> getAllInstSortedAsc(){
        log.info("find a list of institutions in a ascending manner");
        return repo.findAll(Sort.by(Sort.Direction.ASC, "instName"));
    }

    public List<Institution> getAllInstSortedDesc(){
        log.info("find a list of institutions in a descending manner");
        return repo.findAll(Sort.by(Sort.Direction.DESC, "instName"));
    }

    public Optional<Institution> getInst(int id){
        log.info("getting record with id: {}", id);
        return repo.findById(id);
    }

    public Optional<Institution> getInst(String name){
        log.info("getting record with name: {}", name);
        return repo.findByInstName(name);
    }


    public String editInstName(String newName, String dbName){

        log.info("getting record with name: {}", dbName);
        Institution inst1 = repo.findByInstName(dbName).orElse(new Institution("empty"));

        log.info("getting record with name: {}", newName);
        Institution inst2 = repo.findByInstName(newName).orElse(new Institution("empty"));
        String name_inst_2 = inst2.getInstName().toUpperCase();
        String name_inst_1 = inst1.getInstName().toUpperCase();

        if(name_inst_2.equals(name_inst_1) && name_inst_2 != null){
            log.info("this name: {}, already exists", newName);
            return "Name already exists";
        }else{
            log.info("changing the name: {} to the new name: {} ", dbName, newName);
            inst1.setInstName(newName);
            repo.save(inst1);
            return "Record name updated";
        }


    }

    public String deleteInst(String instName){
        log.info("deleting record of institution");
        log.info("checking if institution exists");
        Institution institution = repo.findInstitutionByInstName(instName).orElseThrow();
        log.info("checking if institution has courses");
        if(institution.getCourses().isEmpty()){
            log.info("deleting the institution");
            repo.delete(institution);
            return "institution deleted";
        }else {
            log.info("the institution has courses being offered");
            return "the institution has courses being offered";
        }
    }



}
