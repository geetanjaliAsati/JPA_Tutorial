package com.SpringDataJPATutorial.jpaTutorial.repository;

import com.SpringDataJPATutorial.jpaTutorial.entity.Course;
import com.SpringDataJPATutorial.jpaTutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacherWithCourse() {
        Course courseDSA = Course.builder()
                .title("DSA")
                .credit(5)
                .build();

        Course courseJAVA = Course.builder()
                .title("JAVA")
                .credit(5)
                .build();
//1:47 hr
        Teacher teacher = Teacher.builder()
                .firstName("Seema")
                .lastName("Asati")
//                .courses((List<Course>) courses)
//                .courses(List.of(courseDSA, courseJAVA))
                .build();
        teacherRepository.save(teacher);

    }
}