package com.kosvad9.service;

import com.kosvad9.database.entity.Staff;
import com.kosvad9.database.enums.RoleStaff;
import com.kosvad9.database.enums.StatusStaff;
import com.kosvad9.database.repository.StaffRepository;
import com.kosvad9.dto.StaffCreateDto;
import com.kosvad9.dto.StaffDto;
import com.kosvad9.mapper.CreateDtoStaffMapper;
import com.kosvad9.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class StaffService {
    private final Mapper<StaffCreateDto, Staff> createDtoStaffMapper;
    private final Mapper<Staff, StaffDto> staffDtoMapper;
    private final StaffRepository staffRepository;

    public StaffDto createStaff(StaffCreateDto staffCreateDto){
        Staff staff = createDtoStaffMapper.map(staffCreateDto);
        return staffDtoMapper.map(staffRepository.save(staff));
    }

    public boolean changeStatus(Long staffId, StatusStaff newStatus){
        Optional<Staff> maybeStaff = staffRepository.findById(staffId);
        maybeStaff.ifPresent(staff -> staff.setStatus(newStatus));
        maybeStaff.ifPresent(staffRepository::save);
        return maybeStaff.isPresent();
    }

    public boolean changeRole(Long staffId, RoleStaff newRole){
        Optional<Staff> maybeStaff = staffRepository.findById(staffId);
        maybeStaff.ifPresent(staff -> staff.setRole(newRole));
        maybeStaff.ifPresent(staffRepository::save);
        return maybeStaff.isPresent();
    }
}
