package com.kosvad9.database.repository;

import com.kosvad9.database.entity.Card;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    @Query(nativeQuery = true,
            value = "SELECT nextval('card_number_seq')")
    Long getNextCardIdentifier();

    List<Card> findCardsByAccount_Id(Long accountId);

    @EntityGraph(attributePaths = "account")
    Card findCardById(Long cardId);

    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query("update Card c set c.account.amount = c.account.amount - :sum where c.id = :cardId")
    void subtractMoneyP2P(Long cardId, BigDecimal sum);

    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query("update Card c set c.account.amount = c.account.amount - :sum where c.number = :cardNumber")
    void addMoneyP2P(String cardNumber, BigDecimal sum);
}
