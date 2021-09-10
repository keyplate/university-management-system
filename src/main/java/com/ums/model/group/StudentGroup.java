package com.ums.model.group;

import lombok.Data;
import com.ums.model.student.Student;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "students_group")
public class StudentGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "group")
    private Set<Student> students;

}
