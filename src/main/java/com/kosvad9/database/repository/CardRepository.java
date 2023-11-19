package com.kosvad9.database.repository;

import com.kosvad9.database.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    @Query(nativeQuery = true,
            value = "SELECT nextval('card_number_seq')")
    Long getNextCardIdentifier();
    List<Card> findCardsByAccount_Id(Long accountId);
}
