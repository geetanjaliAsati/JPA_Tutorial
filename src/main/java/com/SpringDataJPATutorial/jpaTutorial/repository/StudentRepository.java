package com.SpringDataJPATutorial.jpaTutorial.repository;

import com.SpringDataJPATutorial.jpaTutorial.entity.StudentTable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//long is primititve Type argument so here it cannot use as primary key for JpaRepository, Long is not
@Repository
public interface StudentRepository extends JpaRepository<StudentTable, Long> {
    //Find all records that matches with firstName parameter
    public List<StudentTable> findStudentByFirstName(String firstName);

    //Find all records of the students that containing passed parameters characters in firstname
//    public List<StudentTable> findByFirstNameContaining(String firstName);

    @Query("SELECT s FROM StudentTable s WHERE s.firstName LIKE %:firstName%")
    List<StudentTable> findStudentsContainingCharacters(@Param("firstName") String firstName);

    List<StudentTable> findByLastNameNotNull();
//Guardian is the property name , Name is the property name of the guardian object
    List<StudentTable> findByGuardianName(String guardianName);

    List<StudentTable> findByEmailIdAndLastName(String emailId, String lastName);

    List<StudentTable> findByFirstNameAndLastName(String firstName, String lastName);

//    JPQL QUERY
//    @Query("SELECT s FROM StudentTable s WHERE s.emailId = :emailId") will also work
    @Query("SELECT s FROM StudentTable s WHERE s.emailId = ?1")
    StudentTable getStudentsByEmailId(@Param("emailId") String emailAddress);

//    emailId, Here name of the @Param or in @Query annotation should be one of the fields of StudentTable class, spelling should be same as fields of StudentTable
    @Query("SELECT s.firstName FROM StudentTable s WHERE s.emailId = ?1")
    String getStudentFirstNameByEmailId(@Param("emailId") String emailAddress);

//    NATIVE QUERY
    @Query(value = "SELECT * FROM schoolDatabase.tbl_student AS s WHERE s.email_address = :emailAddress", nativeQuery = true)
    StudentTable getStudentByEmailIdNativeQuery(String emailAddress);

// Named parameter query
    @Query(value = "SELECT * FROM schoolDatabase.tbl_student AS s WHERE s.email_address = :email_address", nativeQuery = true)
    StudentTable getStudentByEmailIdNativeQueryNamedPara(@Param("email_address") String emailAddress);

    @Modifying
    @Transactional
    @Query(value = " UPDATE tbl_student set first_name = :firstName WHERE email_address = :emailId", nativeQuery = true)
    int updateStudentFirstNameByEmailId(String firstName, String emailId);
}
