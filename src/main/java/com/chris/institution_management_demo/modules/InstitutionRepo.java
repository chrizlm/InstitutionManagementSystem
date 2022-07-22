package com.chris.institution_management_demo.modules;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;


@Repository
public interface InstitutionRepo extends JpaRepository<Institution,Integer> {

    Optional<Institution> findByInstName(String name);
    Optional<Institution> findInstitutionByInstName(String name);
    Set<Course> findByCourses(Course course);


}
