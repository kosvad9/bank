package com.kosvad9.database.repository;

import com.kosvad9.IntegrationTestBase;
import com.kosvad9.database.entity.Client;
import com.kosvad9.database.entity.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@RequiredArgsConstructor
public class ClientRepositoryTest extends IntegrationTestBase {

    private final ClientRepository userRepository;

    @Test
    public void testFindById(){
        Client client = userRepository.findById(1L).orElse(null);
        assertThat(client).isNotNull();
        System.out.println(client);
    }
}
