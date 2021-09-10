package com.ums.model.dean;

import com.ums.model.account.Account;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "account_id")
public class Dean extends Account {
}
