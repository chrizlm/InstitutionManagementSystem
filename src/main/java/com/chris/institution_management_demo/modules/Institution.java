package com.chris.institution_management_demo.modules;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/*
* Institution class is related with course snd students */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Institution implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int instId;

    private String instName;

    @OneToMany(mappedBy = "institution", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Course> courses;

    @OneToMany(mappedBy = "institutionstu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Student> students;

    public Institution(String instName) {
        this.instName = instName;
    }
}
