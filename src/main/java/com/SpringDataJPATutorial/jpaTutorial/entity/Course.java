package com.SpringDataJPATutorial.jpaTutorial.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data//all in one annotation provides getters setters toString etc
@Builder//simplifies object creation
public class Course {
    @Id//marks field the primary key of the entity.
    @SequenceGenerator(//auto-incrementing columns, generate unique identifier values for database entities in Java applications,
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseId;
    private String title;
    private Integer credit;
//    It is bidirectional mapping, because we have already joined the course and courseMaterial class by foreign key so to get the courseMaterial object data into course class we can not use foreign key concept here
//    So to getting the object of courseMaterial in the course class i have created separate field and now i am defining the relationship b/w these two tables by using OneToOne relationship and the current courseMaterial field will point to course field of courseMaterial
 //So to avoid writing again and again mapping by using mappedBy attribute i am telling that this relationship has already been defined in the courseMaterial course field so see that
 //To get the reference of the courseMaterial i am using here OneToOne Mapping that is here bidirectional
 //Here by usingmappedBy i am saying that this courseMaterial field of course class is mapped by Course Material class field or column course
    @OneToOne(mappedBy = "course")
    private CourseMaterial courseMaterial;

    @JoinColumn(name = "teacher_id", referencedColumnName = "teacherId")
    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "student_course_mapping",
            joinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "studentId"
            )
    )
    private List<StudentTable> studentTableList = new ArrayList<>();

    public void addStudents(StudentTable studentTableObj) {
        if(studentTableObj == null) {
            this.studentTableList = new ArrayList<>();
        }
        this.studentTableList.add(studentTableObj);
    }
}
