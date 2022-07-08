package com.chris.institution_management_demo.modules;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {
    List<Student> findByCourse(Course course);
    List<Student> findByInstitution(Institution institution, Sort sort, Pageable pageable);
    List<Student> findByInstitution(Institution institution);

    Optional<Student> findByStudentName(String name);
}
