package com.kosvad9.controller;

import com.kosvad9.dto.ClientDto;
import com.kosvad9.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ResourceController {
    private final ImageService imageService;
    @GetMapping("/avatar")
    @ResponseBody
    public ResponseEntity<byte[]> getAvatar(@SessionAttribute("user") ClientDto clientDto){
        //if (!Objects.equals(clientDto.id(), id)) return null;
        return imageService.get(clientDto.image())
                .map(content -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                        .contentLength(content.length)
                        .body(content))
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @PostMapping("/avatar")
    public String uploadAvatar(MultipartFile avatar,
                               @SessionAttribute("user") ClientDto clientDto){
        imageService.upload(avatar, clientDto.id());
        return "redirect:/accounts";
    }
}
