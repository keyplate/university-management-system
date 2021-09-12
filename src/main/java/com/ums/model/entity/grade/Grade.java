package com.ums.model.entity.grade;

import com.ums.model.entity.course.Course;
import com.ums.model.entity.student.Student;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "grade")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(nullable = false)
    private int value;
}

