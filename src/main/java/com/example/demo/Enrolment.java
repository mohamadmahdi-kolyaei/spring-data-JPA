package com.example.demo;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Enrolment {
    @EmbeddedId
    private EnrolmentId id;  // so we have EnrolmentId Composite key which we marked it as embeddable and now in the enrolment class we want to embed(means : tabie) as our id
    @ManyToOne
    @MapsId("studentId") // in enrolmentId class
    @JoinColumn(
            name = "student_id",
            foreignKey = @ForeignKey(name = "enrolment_student_id_fk")
    )
    private Student student;
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(
            name = "course_id", // in enrolmentId class
            foreignKey = @ForeignKey(name = "enrolment_course_id_fk")
    )
    private Course course;
    @Column(
            nullable = false
    )
    private LocalDateTime createdAt;

    public Enrolment(Student student, Course course , LocalDateTime createdAt) {
        this.student = student;
        this.course = course;
        this.createdAt = createdAt;
    }


    public Enrolment(EnrolmentId id, Student student, Course course , LocalDateTime createdAt) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.createdAt = createdAt;
    }

    public Enrolment() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public EnrolmentId getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
