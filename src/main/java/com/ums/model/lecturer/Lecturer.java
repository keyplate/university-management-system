package com.ums.model.Lecturer;

import com.ums.model.Account;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "account_id")
public class Lecturer extends Account {
}
