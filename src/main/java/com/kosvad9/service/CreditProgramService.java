package com.kosvad9.service;

import com.kosvad9.database.repository.CreditProgramRepository;
import com.kosvad9.dto.CreditProgramDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class CreditProgramService {
    private final CreditProgramRepository creditProgramRepository;

    public List<CreditProgramDto> getAllCreditProgram(){
        return creditProgramRepository.findAllBy();
    }
}
