package com.ums.model.auth;

import lombok.Getter;

@Getter
public enum Roles {
    ROLE_STUDENT("STUDENT"),
    ROLE_LECTURER("STUDENT"),
    ROLE_DEAN("STUDENT");

    String simpleName;

    Roles(String simpleName) {
        this.simpleName = simpleName;
    }
}
