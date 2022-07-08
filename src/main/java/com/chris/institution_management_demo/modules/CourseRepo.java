package com.chris.institution_management_demo.modules;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/*
 * course repository
 * */

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {
    List<Course> findByInstitution(Institution institution, Sort sort);
    Optional<Course> findCourseByCourseName(String name);
}
