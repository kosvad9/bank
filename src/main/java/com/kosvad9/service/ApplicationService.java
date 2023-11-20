package com.kosvad9.service;

import com.kosvad9.database.entity.Application;
import com.kosvad9.database.repository.ApplicationRepository;
import com.kosvad9.dto.ApplicationDto;
import com.kosvad9.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final Mapper<Application, ApplicationDto> applicationDtoMapper;
    private final int PAGESIZE = 10;

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
}
