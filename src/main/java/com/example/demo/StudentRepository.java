package com.example.demo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student , Long> {
    @Query("select s from Student s where s.email = ?1 ") // jpql query
    Optional<Student> findStudentByEmail(String email);  // optional because email is unique  and doesn't return a list   optional is a class
    @Query("select s from Student s where s.firstName = ?1 and  s.age>=?2 ") // and then you can change the name of your method
    List<Student> findStudentByFirstNameEqualsAndAgeGreaterThanEqual(String firstName , Integer age);

    // if you use native query that means that if you change your database for example from postgres to mysql the query might not work
    // however if you use jpql this will work because this in not database specific , it doesn't matter to database
    // you shouldn't use native query as much as you can

    @Query(value = "select * from student where first_name = ?1 and  age>= ?2" , nativeQuery = true  ) // this and the top of that is the same to do one thing , buy with native query
    List<Student> findStudentByFirstNameEqualsAndAgeGreaterThanEqualNativeQuery(String firstName , Integer age);


//  because we're not expecting anything to be mapped from our database to our entity we need to say @Modifying
    @Transactional
    @Modifying  //it tells spring data that query doesn't need map anything from database into entities and just we are modifying some data in our table with update you must do this , we don't retrieve any data from database
    @Query("delete from Student s where s.id = ?1")
    int deleteStudentById(Long id);
    @Transactional
    void deleteStudentByFirstNameContainsIgnoreCase(String firstName);



    List<Student> findByAge(Integer age);
}
