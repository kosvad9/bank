package com.kosvad9.dto;

import com.kosvad9.database.enums.RoleStaff;
import com.kosvad9.database.enums.StatusStaff;

public record StaffCreateDto(String phoneNumber,
                             String password,
                             String firstName,
                             String lastName,
                             String patronymic,
                             RoleStaff role) {
}
