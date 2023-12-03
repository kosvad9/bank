package com.kosvad9.service;

import com.kosvad9.database.entity.Account;
import com.kosvad9.database.entity.Client;
import com.kosvad9.database.entity.PassportInfo;
import com.kosvad9.database.entity.User;
import com.kosvad9.database.repository.ClientRepository;
import com.kosvad9.dto.AccountDto;
import com.kosvad9.dto.ClientCreateDto;
import com.kosvad9.dto.ClientDto;
import com.kosvad9.mapper.ClientDtoMapper;
import com.kosvad9.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class ClientService {
    private final Mapper<Client, ClientDto> clientDtoMapper;
    private final Mapper<ClientCreateDto, Client> createDtoClientMapper;
    private final Mapper<Account, AccountDto> accountDtoMapper;
    private final ClientRepository clientRepository;

    public List<ClientDto> getClients(){
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(clientDtoMapper::map)
                .toList();
    }

    public List<AccountDto> getAccounts(Long clientId){
        if (clientRepository.findById(clientId).isEmpty()) return null;
        return clientRepository.findById(clientId).get().getAccounts().stream().map(accountDtoMapper::map).toList();
    }

    public ClientDto registration(ClientCreateDto clientCreate){
        Client client = clientRepository.save(createDtoClientMapper.map(clientCreate));
        return clientDtoMapper.map(client);
    }

    public ClientDto getClient(Long clientId){
        return clientRepository.findById(clientId).map(clientDtoMapper::map).orElse(null);
    }

    public boolean removeClient(Long clientId){
        Optional<Client> maybeClient = clientRepository.findById(clientId);
        maybeClient.ifPresent(clientRepository::delete);
        return maybeClient.isPresent();
    }
}
