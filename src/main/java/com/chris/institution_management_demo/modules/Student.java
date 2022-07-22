package com.chris.institution_management_demo.modules;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

    private String studentName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courses_id")
    @JsonBackReference(value = "course-student")
    private Course course;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inst_stu_id")
    @JsonBackReference(value = "inst-student")
    private Institution institution;
}
