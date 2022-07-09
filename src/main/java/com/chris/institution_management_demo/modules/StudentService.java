package com.chris.institution_management_demo.modules;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentService {
    @Autowired
    StudentRepo repo;
    @Autowired
    InstitutionRepo inst_repo;
    @Autowired
    CourseRepo courseRepo;

    public String saveStudent(Student student){
        log.info("saving record for student");
        repo.save(student);
        return "student record saved";
    }

    public Student getStudent(int id){
        log.info("getting student with id: {}", id);
        return repo.findById(id).orElseThrow();
    }


    public List<Student> getAllStudents(){
        log.info("getting list of all students");
        return repo.findAll();
    }

    public List<Student> getAllStudentsByInstitution(String instName){
        log.info("getting list of all students");
        Institution institution = inst_repo.findInstitutionByInstName(instName).orElseThrow();
        return repo.findByInstitution(institution, (Pageable) Pageable.ofSize(10).getSort().getOrderFor("course"));
    }


    public List<Student> getAllStudentsAsc(){
        log.info("getting list of all students in ascending order");
        return repo.findAll(Sort.by(Sort.Direction.ASC, "studentName"));
    }

    public List<Student> getAllStudentsDesc(){
        log.info("getting list of all students in descending order");
        return repo.findAll(Sort.by(Sort.Direction.DESC, "studentName"));
    }

    public String editName(String newName, String dbName){
        log.info("changing name of student");
        Student student = repo.findByStudentName(dbName).orElseThrow();
        student.setStudentName(newName);
        return "Name changed";
    }

    public String editCourse(String courseName, String dbName){
        log.info("changing course of student");
        Student student = repo.findByStudentName(dbName).orElseThrow();
        student.getCourse().setCourseName(courseName);
        return "Name changed";
    }

    public String deleteStudent(int id){
        log.info("deleting student with id: {}", id);
        repo.deleteById(id);
        return "student deleted";
    }


    public String transferStudent(int studentId, String instName, String courseName){
        log.info("check if student exists in the university");
        Student student = repo.findById(studentId).orElseThrow();
        Institution institution = inst_repo.findInstitutionByInstName(instName).orElseThrow();
        Course course = courseRepo.findCourseByCourseName(courseName).orElseThrow();
        if(institution != null && course != null){
            log.info("transfering student to: {} to do {}", instName, courseName );
            student.getInstitution().setInstName(instName);
            student.getCourse().setCourseName(courseName);
            repo.save(student);
            return "student transfered";
        } else {
            log.info("could not transfer student");
            return "could not transfer student check name of institution or course or both";
        }
    }


}
