package com.ums.model.entity.dean;

import com.ums.model.entity.account.Account;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "account_id")
public class Dean extends Account {
}
