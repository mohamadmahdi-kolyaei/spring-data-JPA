package com.example.demo;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable  // this class will be embeddable into another entity
public class EnrolmentId  implements Serializable {   // we are going to take this class in a second and embed it into another entity
    private Long studentId;   // we are creating the enrolment instead of JPA with joinTable annotation

    private Long courseId;

    public EnrolmentId(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public EnrolmentId() {
    }

    public Long getStudent_id() {
        return studentId;
    }

    public void setStudent_id(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourse_id() {
        return courseId;
    }

    public void setCourse_id(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrolmentId that = (EnrolmentId) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}
