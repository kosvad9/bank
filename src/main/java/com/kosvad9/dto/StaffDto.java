package com.kosvad9.dto;

import com.kosvad9.database.enums.RoleStaff;
import com.kosvad9.database.enums.StatusStaff;

public record StaffDto(Long id,
                       String phoneNumber,
                       String firstName,
                       String lastName,
                       String patronymic,
                       String role,
                       String status) {
}
