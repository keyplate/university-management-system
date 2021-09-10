package com.ums.model.subject;

import javax.persistence.*;
import java.util.Set;

import com.ums.model.course.Course;
import com.ums.model.lecturer.Lecturer;
import lombok.Data;

@Entity
@Data
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(nullable = false, unique = true)
    private String title;

    @OneToMany(mappedBy = "subject")
    private Set<Course> courses;
}
