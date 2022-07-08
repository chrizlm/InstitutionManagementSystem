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

    public String saveInst(Institution institution){
        log.info("record of {} saved!", institution.getInstName());
        repo.save(institution);


        Optional<Institution> inst = repo.findInstitutionByInstName(institution.getInstName());
        String db_name = inst.get().getInstName().toUpperCase();
        String search_name = institution.getInstName().toUpperCase();

        if(db_name.equals(search_name) && db_name != null){
            log.info("there exists an institution with such a name");
            return "Name already exists";
        }else{
            repo.save(institution);
            log.info("saving record ....");
            return "Institution saved";
        }


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
        ArrayList<Course> courseList = new ArrayList<>(courseSet);
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
        return repo.findInstitutionByInstName(name);
    }


    public String editInstName(String newName, String dbName){

        log.info("getting record with name: {}", dbName);
        Institution inst1 = repo.findInstitutionByInstName(dbName).orElseThrow();

        log.info("getting record with name: {}", newName);
        Institution inst2 = repo.findInstitutionByInstName(newName).orElse(new Institution("empty"));
        String name_inst_2 = inst2.getInstName().toUpperCase();

        if(name_inst_2.equals(newName.toUpperCase()) && name_inst_2 != null){
            log.info("this name: {}, already exists", newName);
            return "Name already exists";
        }else{
            log.info("changing the name: {} to the new name: {} ", dbName, newName);
            inst1.setInstName(newName);
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
