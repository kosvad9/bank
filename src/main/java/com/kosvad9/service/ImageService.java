package com.kosvad9.service;

import com.kosvad9.database.entity.Client;
import com.kosvad9.database.entity.User;
import com.kosvad9.database.repository.ClientRepository;
import com.kosvad9.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    @Value("${app.image.bucket:/avatars}")
    private final String bucket;

    private final ClientRepository clientRepository;

    @SneakyThrows
    public Optional<byte[]> get(String imagePath){
        Path fullPath = Path.of(bucket, imagePath);
        return Files.exists(fullPath) ? Optional.of(Files.readAllBytes(fullPath)):Optional.empty();
    }

    @SneakyThrows
    public void upload(MultipartFile avatar, Long id){
        Client client = clientRepository.getReferenceById(id);
        String imagePath = String.join("_", id.toString(), avatar.getOriginalFilename());
        client.setImage(imagePath);
        clientRepository.save(client);
        Path fullPath = Path.of(bucket, imagePath);
        try(InputStream stream = avatar.getInputStream()){
            Files.createDirectories(fullPath.getParent());
            Files.write(fullPath, stream.readAllBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }

    }
}
