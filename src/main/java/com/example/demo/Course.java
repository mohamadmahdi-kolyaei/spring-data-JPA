package com.example.demo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;
    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String department;
//    @ManyToMany(
//            mappedBy = "courses"
//    )
//    private List<Student>students = new ArrayList<>();
    @OneToMany(
            cascade = {CascadeType.PERSIST , CascadeType.REMOVE},
            mappedBy = "course" // inside enrolment class
    )
    private List<Enrolment> enrolments = new ArrayList<>();

    public Course(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public Course() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

//    public List<Student> getStudents() {
//        return students;
//    }

    public Long getId() {
        return id;
    }
    public List<Enrolment> getEnrolments() {
        return enrolments;
    }

    public void addEnrolment(Enrolment enrolment){
        if ( ! this.enrolments.contains(enrolment)){
            this.enrolments.add(enrolment);
        }
    }

    public void removeEnrolment(Enrolment enrolment){
        if (enrolments.contains(enrolment)){
            this.enrolments.remove(enrolment);
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
