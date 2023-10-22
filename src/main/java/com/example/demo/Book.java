package com.example.demo;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity

public class Book {
    @Id
    @GeneratedValue
    @Column(updatable = false)
    private Long id;
    @Column(
    name = "created_at",
    nullable = false )
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private String bookName;
    @ManyToOne
    @JoinColumn(
            name = "student_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "student_book_fk")
    )
    private Student student;

    public Book() {
    }

    public Book( String bookName , LocalDateTime createdAt) {  // we don't have a student in constructor  because it will set in the addBook method
        this.bookName = bookName;
        this.createdAt = createdAt;

    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", createdAt=" +
                ", bookName='" + bookName + '\'' +
                ", student=" + student +
                '}';
    }
}
