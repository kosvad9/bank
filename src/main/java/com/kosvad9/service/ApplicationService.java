package com.kosvad9.service;

import com.kosvad9.database.entity.*;
import com.kosvad9.database.enums.StatusApplication;
import com.kosvad9.database.repository.*;
import com.kosvad9.dto.ApplicationCreateDto;
import com.kosvad9.dto.ApplicationDto;
import com.kosvad9.mapper.Mapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final Mapper<Application, ApplicationDto> applicationDtoMapper;
    private final AccountService accountService;
    private final CreditRepository creditRepository;
    private final CreditProgramRepository creditProgramRepository;
    private final ClientRepository clientRepository;
    private final int PAGESIZE = 10;

    public List<ApplicationDto> getAllApplications(Long clientId){
        return applicationRepository.getAllByClient_IdOrderByDateDesc(clientId).stream()
                .map(applicationDtoMapper::map)
                .toList();
    }

    public void createApplication(ApplicationCreateDto applicationCreateDto, Long clientId){
        CreditProgram creditProgram = creditProgramRepository.getReferenceById(applicationCreateDto.creditProgramId());
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Сущность клиент не найдена!"));
        Application application = Application.builder()
                .date(LocalDate.now())
                .status(StatusApplication.CREATED)
                .program(creditProgram)
                .periodMonth(applicationCreateDto.periodMonth())
                .amount(applicationCreateDto.amount())
                .build();
        applicationRepository.save(application);
    }

    public Page<ApplicationDto> getFirstPageApplications(){
        Pageable pageRequest = PageRequest.of(0, PAGESIZE, Sort.by("date"));
        Page<Application> slice = applicationRepository.findAllBy(pageRequest);
        return slice.map(applicationDtoMapper::map);
    }

    public Page<ApplicationDto> getNextPageApplications(Page<?> page){
        if (page.hasNext()){
            Page<Application> slice = applicationRepository.findAllBy(page.nextPageable());
            return slice.map(applicationDtoMapper::map);
        }
        else
            return null;
    }

    public Page<ApplicationDto> getPrevPageApplications(Page<?> page){
        if (page.hasPrevious()){
            Page<Application> slice = applicationRepository.findAllBy(page.previousPageable());
            return slice.map(applicationDtoMapper::map);
        }
        else
            return null;
    }

    public Page<ApplicationDto> getPageOfNumberApplications(Page<?> page, int withPage){
        Page<Application> slice = applicationRepository.findAllBy(page.getPageable().withPage(withPage));
        return slice.map(applicationDtoMapper::map);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean approveApplication(Long applicationId){
        Application application = applicationRepository.getReferenceById(applicationId);
        if (application.getStatus() != StatusApplication.CREATED) return false;
        application.setStatus(StatusApplication.APPROVED);
        applicationRepository.save(application);
        Credit newCredit = Credit.builder()
                .client(application.getClient())
                .dateEnd(ChronoUnit.MONTHS.addTo(LocalDate.now(), application.getPeriodMonth()))
                .amount(application.getAmount())
                .debt(application.getAmount())
                .interestRate(application.getProgram().getInterestRate())
                .build();
        newCredit = creditRepository.save(newCredit);
        //Создаем счет и зачисляем деньги на счет клиента
        accountService.createAccountOfCredit(application.getClient().getId(), application.getAmount());
        return true;
    }

    public boolean rejectApplication(Long applicationId, String reason){
        Application application = applicationRepository.getReferenceById(applicationId);
        if (application.getStatus() != StatusApplication.CREATED) return false;
        application.setStatus(StatusApplication.REJECTED);
        application.setDescription(reason);
        applicationRepository.save(application);
        return true;
    }
}
