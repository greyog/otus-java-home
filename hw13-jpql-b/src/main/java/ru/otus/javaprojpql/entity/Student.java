package ru.otus.javaprojpql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "STUDENTS")
@NoArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    private UUID id;

    @Column(name = "NAME", length = 20, nullable = false)
    private String name;


    @OneToMany(mappedBy = "student")
    private Set<Contact> contactSet;

    @ManyToMany
    @JoinTable(name = "STUDENTS_COURSES",
            joinColumns = @JoinColumn(name = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    private Set<Course> courseSet;
}
