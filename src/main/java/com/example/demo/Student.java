package com.example.demo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Student")
@Table(
        name = "student"
)
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",  // in table name
            sequenceName = "student_sequence" , // name of sequence
            allocationSize = 1 // how much sequence increase   start default 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"  // sequence name

    )
    @Column(
            name = "id",
            updatable = false
    )
    private long id;
    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;
    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;
    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    private String email;
    @Column(
            name = "age",
            nullable = false
    )
    private Integer age;
    @OneToOne(
            mappedBy = "student" ,
            orphanRemoval = true , // if i delete the student the studentIdCard also be gone
            cascade = {CascadeType.PERSIST , CascadeType.REMOVE}

    )
    private StudentIdCard studentIdCard;
    @OneToMany(
            mappedBy = "student",
            cascade = {CascadeType.PERSIST , CascadeType.REMOVE}, // whenever we delete   we also delete  all children in this case books , when we insert a  student ,also book will be inserted
            orphanRemoval = true ,
            fetch = FetchType.EAGER
    )
    private List<Book> books = new ArrayList<>() ;
//    @ManyToMany(
//            cascade = {CascadeType.PERSIST , CascadeType.REMOVE}
//    )
//    @JoinTable(
//            name = "enrolment",
//            joinColumns = @JoinColumn(
//                    name = "student_id",
//                    foreignKey = @ForeignKey(name = "enrolment_student_id_fk")
//            ) ,
//            inverseJoinColumns = @JoinColumn(
//                    name = "course_id",
//                    foreignKey = @ForeignKey(name = "enrolment_course_id_fk")
//            )
//    )
//    private List<Course> courses = new ArrayList<>();   // it is for when we use JPA to creat join table
    @OneToMany(
            cascade = {CascadeType.PERSIST , CascadeType.REMOVE},
            mappedBy = "student" // inside enrolment class
    )
    private List<Enrolment> enrolments = new ArrayList<>();
    public Student( String firstName, String lastName, String email, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Student() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setStudentIdCard(StudentIdCard studentIdCard) {
        this.studentIdCard = studentIdCard;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public void addBook(Book book){
        if (!this.books.contains(book)){
            this.books.add(book);
            book.setStudent(this);
        }
    }

    public void removeBook(Book book){
        if (this.books.contains(book)){
            this.books.remove(book);
            book.setStudent(null);
        }
    }

//    public void enrolToCourse(Course course){
//        if (! courses.contains(course)){
//            this.courses.add(course);
//            course.getStudents().add(this);
//        }
//    }
//
//    public void unEnrolCourse(Course course){
//        if (this.courses.contains(course)){
//            courses.remove(course);
//            course.getStudents().remove(this);
//        }
//    }
//    public List<Course> getCourses() {
//        return courses;
//    }


    public List<Enrolment> getEnrolments() {
        return enrolments;
    }

    public void addEnrolment(Enrolment enrolment){
        if ( ! this.enrolments.contains(enrolment)){
            this.enrolments.add(enrolment);
//            enrolment.setStudent(this);

        }
    }

    public void removeEnrolment(Enrolment enrolment){
        if (enrolments.contains(enrolment)){
            this.enrolments.remove(enrolment);
        }
    }
    public StudentIdCard getStudentIdCard() {
        return studentIdCard;
    }



    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
