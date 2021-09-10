package com.ums.model.student;

import com.ums.model.account.Account;
import com.ums.model.grage.Grade;
import com.ums.model.group.StudentGroup;
import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "account_id")
public class Student extends Account {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private StudentGroup group;

    @OneToMany(mappedBy = "student")
    private Set<Grade> grades;
}
