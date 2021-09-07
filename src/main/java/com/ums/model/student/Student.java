package com.ums.model.Student;

import com.ums.model.Account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "account_id")
public class Student extends Account {
    @Column(name = "group_id")
    private int groupId;
}
