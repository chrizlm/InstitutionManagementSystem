package com.chris.institution_management_demo.modules;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Course service supports the controller with the following
 * save a course
 * get all courses - in ascending and descending orders
 * get a single course through id
 * edit name of course and delete the course
 *
 * */

@Service
@Slf4j
public class CourseService {
    @Autowired
    CourseRepo repo;
    @Autowired
    InstitutionRepo institutionRepo;

    public String saveCourse(Course course){

        Course course1 = repo.findCourseByCourseName(course.getCourseName()).orElse(new Course("empty"));

        String course1_name = course1.getCourseName().toUpperCase();

        if(course1_name.equals(course.getCourseName().toUpperCase())){
            log.info("Course name: {} , already exists ", course1_name);
            return "Name already exists";
        } else {
            log.info("Saving record for course");
            repo.save(course);
            return "Course saved";
        }

        //check if name exists
    }

    public String addCourseToInstitution(String courseName, String institutionName){
        log.info("adding course to an institution");
        Course course = repo.findCourseByCourseName(courseName).orElseThrow();
        Institution institution = institutionRepo.findInstitutionByInstName(institutionName).orElseThrow();

        institution.getCourses().add(course);
        return "course added to institution";
    }

    public List<Course> getAllCourses(){
        log.info("Getting a list of courses ....");
        return repo.findAll();
    }

    public Course getCourse(int id){
        log.info("getting course with name: {}", id);
        return repo.findById(id).orElseThrow();
    }

    public Course getCourse(String name){
        log.info("getting course with name: {}", name);
        return repo.findCourseByCourseName(name).orElseThrow();
    }

    public List<Course> getAllCoursesAsc(){
        log.info("Getting list of courses in ascending order ....");
        return repo.findAll(Sort.by(Sort.Direction.ASC, "courseName"));
    }

    public List<Course> getAllCoursesDesc(){
        log.info("Getting list of courses in descending order ....");
        return repo.findAll(Sort.by(Sort.Direction.DESC, "courseName"));

    }

    public String editCourse(String newName, String dbName){
        log.info("getting record with name: {}", dbName);
        Course course = repo.findCourseByCourseName(dbName).orElseThrow();

        log.info("getting record with name: {}", newName);
        Course course1 = repo.findCourseByCourseName(newName).orElse(new Course("empty"));

        String course1_name = course1.getCourseName().toUpperCase();

        if(course1_name.equals(newName.toUpperCase())){
            log.info("Course name: {} , already exists ", course1_name);
            return "Name already exists";
        } else {
            log.info("changing the name: {} to the new name: {} ", dbName, newName);
            course.setCourseName(newName);
            return "Record name updated";
        }

        //check if name exists
    }

    public String deleteCourse(String courseName){
        log.info("checking if course exists");
        Course course = repo.findCourseByCourseName(courseName).orElseThrow();

        log.info("check if the courses have been assigned students");
        if(course.getStudents().isEmpty()){
            log.info("deleting course");
            repo.delete(course);
            return "course has been deleted";
        }else {
            log.info("the course have been assigned to students");
            return "the course has been assigned to students";
        }

    }


}
