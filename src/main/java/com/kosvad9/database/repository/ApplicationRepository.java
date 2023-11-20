package com.kosvad9.database.repository;

import com.kosvad9.database.entity.Application;
import com.kosvad9.dto.ApplicationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    @EntityGraph(attributePaths = {"program","client"})
    @Query(value = "select app from Application app",
            countQuery = "select count(distinct app.id) from Application app")
    Page<Application> findAllBy(Pageable pageable);
}
