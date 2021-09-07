package com.ums.model.Dean;

import com.ums.model.Account;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "account_id")
public class Dean extends Account {
}
