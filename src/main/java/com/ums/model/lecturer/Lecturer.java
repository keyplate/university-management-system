package com.ums.model.lecturer;

import com.ums.model.account.Account;
import com.ums.model.course.Course;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "account_id")
public class Lecturer extends Account {

    @OneToMany(mappedBy = "lecturer")
    private Set<Course> courses;
}
