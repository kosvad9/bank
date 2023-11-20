package com.kosvad9.database.repository;

import com.kosvad9.database.entity.Credit;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    @Query("select count(c) from Credit c where c.debt > 0 and c.client.id = :clientId")
    Integer countNotPaidCredits(Long clientId);

    @Query("select count(c) from Credit c where c.debt = 0 and c.client.id = :clientId")
    Integer countPaidCredits(Long clientId);

    @Modifying
    @Query(value = "update Credit c set c.debt = c.debt - :sum, c.lastPaymentDate = :paymentDate " +
            "where c.id = :creditId")
    Integer pay(Long creditId, BigDecimal sum, LocalDate paymentDate);
}
