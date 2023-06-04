package com.example.springbootreferencesystemrestapi.controller;

import com.example.springbootreferencesystemrestapi.models.Computer;
import com.example.springbootreferencesystemrestapi.service.interfaces.IComputerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    IComputerService computerService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> UploadFile(@RequestParam("file") MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();

            // Десеріалізація JSON-файлу в масив об'єктів Computer
            ObjectMapper objectMapper = new ObjectMapper();
            List<Computer> computers = objectMapper.readValue(bytes, new TypeReference<List<Computer>>() {});

            // Виконання операцій з отриманим масивом computers
            var result = computerService.CreateFromFile(computers);

            return ResponseEntity.ok("File uploaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error uploading the file.");
        }
    }

    @GetMapping(value = "/download")
    public ResponseEntity<byte[]> DownloadFile() {
        try {
            // Отримання списку комп'ютерів
            var computers = computerService.GetAll();

            // Серіалізація списку комп'ютерів у JSON
            ObjectMapper objectMapper = new ObjectMapper();
            byte[] jsonData = objectMapper.writeValueAsBytes(computers);

            // Параметри відповіді
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setContentDispositionFormData("attachment", "computers.json");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}
