package com.kosvad9.database.repository;

import com.kosvad9.database.entity.Client;
import com.kosvad9.database.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @EntityGraph(attributePaths = {"accounts"})
    Optional<Client> findById(Long id);
}
