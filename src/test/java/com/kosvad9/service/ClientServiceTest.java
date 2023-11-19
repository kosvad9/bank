package com.kosvad9.service;

import com.kosvad9.IntegrationTestBase;
import com.kosvad9.database.entity.Client;
import com.kosvad9.dto.ClientCreateDto;
import com.kosvad9.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class ClientServiceTest extends IntegrationTestBase {
    private final ClientService clientService;
    private final ClientCreateDto newClient = new ClientCreateDto("+375299999999",
            "qwerty999",
            "Андрей",
            "Петров",
            "Иванович",
            LocalDate.of(2001,12,3),
            "MP3214321",
            "111111",
            LocalDate.of(2018,8,21));

    @Test
    public void registrationNewClient(){
        ClientDto client = clientService.registration(newClient);
        assertThat(client.id()).isNotNull();
    }
}
