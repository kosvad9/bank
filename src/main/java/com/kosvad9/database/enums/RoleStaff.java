package com.kosvad9.database.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleStaff implements GrantedAuthority {
    ADMIN, MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}
