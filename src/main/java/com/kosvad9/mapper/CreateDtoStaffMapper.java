package com.kosvad9.mapper;

import com.kosvad9.database.entity.Staff;
import com.kosvad9.database.enums.StatusStaff;
import com.kosvad9.dto.StaffCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateDtoStaffMapper implements Mapper<StaffCreateDto, Staff>{
    private final PasswordEncoder passwordEncoder;
    @Override
    public Staff map(StaffCreateDto value) {
        Staff staff =  Staff.builder()
                .phoneNumber(value.phoneNumber())
                .firstName(value.firstName())
                .lastName(value.lastName())
                .patronymic(value.patronymic())
                .role(value.role())
                .status(StatusStaff.ACTIVE)
                .build();
        Optional.ofNullable(value.password())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(staff::setPassword);
        return staff;
    }
}
