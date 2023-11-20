package com.kosvad9.database.repository;

import com.kosvad9.database.entity.CreditProgram;
import com.kosvad9.dto.CreditProgramDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditProgramRepository extends JpaRepository<CreditProgram, Integer> {
    List<CreditProgramDto> findAllBy();
}
