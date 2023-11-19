package com.kosvad9.mapper;

import com.kosvad9.database.entity.Staff;
import com.kosvad9.dto.StaffDto;
import org.springframework.stereotype.Component;

@Component
public class StaffDtoMapper implements Mapper<Staff, StaffDto> {
    @Override
    public StaffDto map(Staff value) {
        return new StaffDto(value.getId(),
                value.getPhoneNumber(),
                value.getFirstName(),
                value.getLastName(),
                value.getPatronymic(),
                value.getRole().toString(),
                value.getStatus().toString());
    }
}
