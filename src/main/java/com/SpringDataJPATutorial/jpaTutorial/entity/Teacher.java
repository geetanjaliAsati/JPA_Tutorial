package com.SpringDataJPATutorial.jpaTutorial.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher {
    @Id
    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence"
    )
    private Long teacherId;
    private String firstName;
    private String lastName;

//    @OneToMany(
//            cascade = CascadeType.ALL//CascadeType.ALL attribute specifies that all operations (including persist, remove, merge, detach, and refresh) should be cascaded from the parent entity (Teacher) to the associated entities (Course).
////   When you perform operations on a Teacher entity, such as persisting, updating, or deleting, you want those operations to also apply to the associated Course entities. This ensures consistency in your database.
////automatically propagating the state transitions (like persisting or deleting) from the parent entity to its children. Without it, you would need to explicitly manage the lifecycle of associated Course entities.
////            Without cascade settings, if you remove a Teacher, associated Course entities might become orphaned (not associated with any Teacher). Cascading remove ensures that when a Teacher is deleted, its associated Course entities are also removed, preventing orphaned records in the database.
//    )
////    The JoinColumn will add the teacherId column into the Course Table as a foreign key
//    @JoinColumn(
//            name = "teacher_id",
//            referencedColumnName = "teacherId"//want to make teacherId the foreign key in the Course Table
//    )
//    private List<Course> courses;
}
