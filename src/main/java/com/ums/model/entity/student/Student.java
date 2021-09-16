package com.ums.model.entity.student;

import com.ums.model.entity.account.Account;
import com.ums.model.entity.grade.Grade;
import com.ums.model.entity.group.StudentGroup;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "account_id")
public class Student extends Account {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = true)
    private StudentGroup group;

    @OneToMany(mappedBy = "student")
    private Set<Grade> grades;

}
