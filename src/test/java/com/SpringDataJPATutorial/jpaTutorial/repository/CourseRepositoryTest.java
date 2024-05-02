package com.SpringDataJPATutorial.jpaTutorial.repository;

import com.SpringDataJPATutorial.jpaTutorial.entity.Course;
import com.SpringDataJPATutorial.jpaTutorial.entity.CourseMaterial;
import com.SpringDataJPATutorial.jpaTutorial.entity.StudentTable;
import com.SpringDataJPATutorial.jpaTutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;
    @Test
    public void findAllCourses() {
        List<Course> courses = courseRepository.findAll();
        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithTeacher() {
        Teacher teacherObj = Teacher.builder()
                .firstName("Priyanshi")
                .lastName("Bhardwaj")
                .build();

        Course course = Course.builder()
                .title("Python")
                .credit(6)
                .teacher(teacherObj)
                .build();
        courseRepository.save(course);
    }

    @Test
    public void findAllPagination() {
        Pageable firstPageWithThreeRecords = PageRequest.of(0,3);
        Pageable secondPageWithTwoRecords = PageRequest.of(0, 2);

        List<Course> courses = courseRepository.findAll(secondPageWithTwoRecords).getContent();

        int totalElements = courseRepository.findAll(secondPageWithTwoRecords).getNumberOfElements();
        int totalPages = courseRepository.findAll(secondPageWithTwoRecords).getTotalPages();

        System.out.println("totalPages = " + totalPages);
        System.out.println("totalElements = " + totalElements);
        System.out.println("courses = " + courses);

    }

    @Test
    public void findAllSorting() {
        Pageable sortByTitle = PageRequest.of(0, 2, Sort.by("title"));
        Pageable sortByCreditDesc = PageRequest.of(0, 2, Sort.by("credit").descending());
        Pageable sortByCreditDescAndTitle = PageRequest.of(0, 2,
                Sort.by("title").descending().and(Sort.by("credit")));

        List<Course> courses = courseRepository.findAll(sortByTitle).getContent();
        System.out.println("courses = " + courses);

    }

    //Not working this method
    @Test
    public void printfindByTitleContaining() {
        Pageable firstPageTenRecords = PageRequest.of(0, 10);
        List<Course> courses = courseRepository.findByTitleContaining("J", firstPageTenRecords).getContent();
        System.out.println("courses = " + courses);
    }

//    These two methods not working
    @Test
    public void saveCourseWithStudentAndTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Rajshree")
                .lastName("Gupta")
                .build();
        StudentTable student = StudentTable.builder()
                .firstName("Poornima")
                .lastName("Agarwal")
                .emailId("poornima@gmail.com")
                .build();

        Course course = Course.builder()
                .teacher(teacher)
                .credit(50)
                .title("AI")
                .build();

        course.addStudents(student);
        courseRepository.save(course);
    }
}