package com.SpringDataJPATutorial.jpaTutorial.repository;

import com.SpringDataJPATutorial.jpaTutorial.entity.Course;
import com.SpringDataJPATutorial.jpaTutorial.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialTest {
    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    public void saveCourseMaterial() {
        Course course = Course.builder()
                .title("AWS web services")
                .credit(500)
                .build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.amazon.com")
                .course(course)
                .build();
//Course(courseId=null, title=Cloud Computing, credit=500) See this if you will try to add courseMaterial without creating the course in the course table then it will give error org.hibernate.TransientPropertyValueException: object references an unsaved transient instance - save the transient instance before flushing : com.SpringDataJPATutorial.jpaTutorial.entity.CourseMaterial.course -> com.SpringDataJPATutorial.jpaTutorial.entity.Course
        courseMaterialRepository.save(courseMaterial);
//        To resolve transient error we use casading to pass the properties from parent to child and courseMaterial is parent here and course is child
    }
//org.hibernate.LazyInitializationException: could not initialize proxy [com.SpringDataJPATutorial.jpaTutorial.entity.Course#1] - no Session, we don't want course while printing the courseMaterial but the toString method bringing the course so we will in the courseMaterial class toString annotation @ToString(exclude = "course") will write
    @Test
    public void printAllCourseMaterials() {
        List<CourseMaterial> courseMaterials = courseMaterialRepository.findAll();
        System.out.println("courseMaterials = " + courseMaterials);
    }

}
// Spring is a popular Java application framework. Spring Boot is an effort to create stand-alone, production-grade Spring-based applications with minimal effort.
// Spring Data JPA to reduce the amount of boilerplate code required to implement the data access object (DAO) layer.
//Spring Data JPA is not a JPA provider. It is a library/framework that adds an extra layer of abstraction on top of our JPA provider (like Hibernate). Spring Data JPA uses Hibernate as a default JPA provider.
