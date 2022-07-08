package com.chris.institution_management_demo.modules;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


/*
* Course create is linked to both Institution class and student through relations
* and contains a name and id attribute
* */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    private String courseName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inst_id", nullable = false)
    private Institution institution;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Student> students;


    public Course(String courseName) {
        this.courseName = courseName;
    }
}
