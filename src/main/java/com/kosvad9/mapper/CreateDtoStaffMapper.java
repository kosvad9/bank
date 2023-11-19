package com.kosvad9.mapper;

import com.kosvad9.database.entity.Staff;
import com.kosvad9.database.enums.StatusStaff;
import com.kosvad9.dto.StaffCreateDto;
import org.springframework.stereotype.Component;

@Component
public class CreateDtoStaffMapper implements Mapper<StaffCreateDto, Staff>{
    @Override
    public Staff map(StaffCreateDto value) {
        return Staff.builder()
                .phoneNumber(value.phoneNumber())
                .password(value.password())
                .firstName(value.firstName())
                .lastName(value.lastName())
                .patronymic(value.patronymic())
                .role(value.role())
                .status(StatusStaff.ACTIVE)
                .build();
    }
}
