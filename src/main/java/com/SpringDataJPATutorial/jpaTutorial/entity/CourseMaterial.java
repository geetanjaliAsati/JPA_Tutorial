package com.SpringDataJPATutorial.jpaTutorial.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
//I am telling bringout to exclude the course column from my courseMaterial class while printing result
@ToString(exclude = "course")
public class CourseMaterial {
    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
//    for mapping one to one course material cant exist without course and course material will consist of course
    private Long courseMaterialId;
    private String url;

//    For every course there will be one course material so it is oneToOne relation ship and by adding one more column of course inside course material table telling the courseId means which course this material is related to and because we are using the course primary key inside the courseMaterial table as a column to reference particular courseId row of the course table that row will call as a foreign key inside courseMaterial table
//    For which particular column we can join the tables course and course material we use joinColumn annotation
@OneToOne(
//    To see available methods available for particualar function press ctr +shift + space
//cascade means to pass the properties from parents to child, it tells that if this column in the Course table is not present then pass this info to the parent
        cascade = CascadeType.ALL,
//        Eager fetch type will bring you course data as well along with courseMaterial
        //Lazy fetch type will only bring courseMaterial it wont bring course data unless you want specifcally ask for that
        fetch = FetchType.LAZY,
        optional = false//it tells whenever you will try to save the course then you also have to save the course matreial with that you cant create course without course material
//org.springframework.dao.DataIntegrityViolationException: not-null property references a null or transient value : com.SpringDataJPATutorial.jpaTutorial.entity.CourseMaterial.course, if we will try to insert the coursematerial without course it will give this exception
)

    @JoinColumn(
            name = "course_id",
            referencedColumnName = "courseId"
    )
    private Course course;

}
