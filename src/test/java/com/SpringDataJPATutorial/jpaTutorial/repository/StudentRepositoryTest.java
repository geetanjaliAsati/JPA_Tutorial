package com.SpringDataJPATutorial.jpaTutorial.repository;

import com.SpringDataJPATutorial.jpaTutorial.entity.Guardian;
import com.SpringDataJPATutorial.jpaTutorial.entity.StudentTable;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;
    @Test
    public void saveStudent() {
        StudentTable student = StudentTable.builder()
                .firstName("Geetanjali")
                .lastName("Asati")
                .emailId("asatigeetanjali@gmail.com")
//                .guardianEmail("devashish@gmail.com")
//                .guardianName("Devashish")
//                .guardianMobile("8989898989")
                .build();

        studentRepository.save(student);
        assertNotNull(student.getStudentId()); // Ensure that student has been saved with an ID
    }


    @Test
    public void printAllStudents() {
        List<StudentTable> studentTableList = studentRepository.findAll();
        System.out.println("student List = " + studentTableList);
    }

    @Test
    public void saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder()
                .name("Malti")
                .email("malti.sharma@gmail.com")
                .mobile("3434343434")
                .build();

        StudentTable studentTableObj = StudentTable.builder()
                .firstName("Shivam")
                .lastName("Sharma")
                .emailId("shivamsharma@gmail.com")
                .guardian(guardian)
                .build();

//        studentRepository.save(studentTableObj);
        // Ensure that attempting to save a student with duplicate email throws DataIntegrityViolationException
        assertThrows(DataIntegrityViolationException.class, () -> {
            studentRepository.save(studentTableObj);
        });
    }

    @Test
    public void printStudentByFirstName() {
        List<StudentTable> students = studentRepository.findStudentByFirstName("Shivam");
        System.out.println("students = " + students);
    }

    @Test
    public void printStudentsContainingCharacters() {
        List<StudentTable> students = studentRepository.findStudentsContainingCharacters("ta");
        System.out.println("students = " + students);
    }
    /*
    * When you name a method findByFirstNameContaining, Spring Data JPA knows that it needs to generate a query to find entities where the firstName field contains a specific string. However, if you name the method findStudentsContainingCharacters, Spring Data JPA won't recognize this as a valid method naming convention.
To achieve the functionality you desire with a custom method name, you can use the @Query annotation to define the query manually in the StudentRepository interface.

     * */

    @Test
    public void printStudentsByLastNameNotNull() {
        List<StudentTable> students = studentRepository.findByLastNameNotNull();
        System.out.println("students Last Name = " + students);
    }

    @Test
    public void printStudentsByGuardianName() {
        List<StudentTable> students = studentRepository.findByGuardianName("malti");
        System.out.println("students Guardian= " + students);
    }

    @Test
    public void printStudentsByEmailAddressAndLastname() {
        List<StudentTable> students = studentRepository.findByEmailIdAndLastName("asatigeetanjali@gmail.com", "Asati");
        System.out.println("printStudentsByEmailAddressAndLastname= " + students);
    }

    @Test
    public void printStudentsByFirstNameAndLastname() {
        List<StudentTable> students = studentRepository.findByFirstNameAndLastName("Geetanjali", "Asati");
        System.out.println("PrintByFirstNameAndLastName= " + students);
    }

    @Test
    public void printStudentsByEmailAddress() {
        StudentTable students = studentRepository.getStudentsByEmailId("asatigeetanjali@gmail.com");
        System.out.println("printStudentsByEmailAddress= " + students);
    }

    @Test
    public void printGetStudentFirstNameByEmailId() {
        String studentFirstName = studentRepository.getStudentFirstNameByEmailId("asatigeetanjali@gmail.com");
        System.out.println("printGetStudentFirstNameByEmailId= " + studentFirstName);
    }
//How to fetch data from database by using Native query(using the fields of database) or JPQL query (using standard jpa query operations and using java class fields name as column)
    @Test
    public void printStudentByEmailIdNativeQuery() {
        StudentTable student = studentRepository.getStudentByEmailIdNativeQuery("asatigeetanjali@gmail.com");
        System.out.println("printStudentByEmailIdNativeQuery= " + student);
    }

    @Test
    public void printStudentByEmailIdNativeQueryNamedPara() {
        StudentTable student = studentRepository.getStudentByEmailIdNativeQueryNamedPara("asatigeetanjali@gmail.com");
        System.out.println("getStudentByEmailIdNativeQueryNamedPara= " + student);
    }

    @Test
    public void updateStudentFirstNameByEmailIdTest() {
        studentRepository.updateStudentFirstNameByEmailId("Ramakant", "shivamsharma@gmail.com");
        System.out.println("First Name updated successfully!");
        printAllStudents();
    }
}