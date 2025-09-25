package com.example.filemanager.controller;

import com.example.filemanager.model.FileEntity;
import com.example.filemanager.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileRepository fileRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam(value = "expireMinutes", required = false) Long expireMinutes) {
        try {
            FileEntity fileEntity = new FileEntity();
            fileEntity.setFileName(file.getOriginalFilename());
            fileEntity.setFileType(file.getContentType());
            fileEntity.setSize(file.getSize());
            fileEntity.setData(file.getBytes());
            fileEntity.setUploadTime(LocalDateTime.now());
            if (expireMinutes != null) {
                fileEntity.setExpireTime(LocalDateTime.now().plusMinutes(expireMinutes));
            }
            fileRepository.save(fileEntity);
            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Could not upload file: " + e.getMessage());
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        Optional<FileEntity> optionalFile = fileRepository.findById(id);
        if (optionalFile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        FileEntity fileEntity = optionalFile.get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileEntity.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFileName() + "\"")
                .body(fileEntity.getData());
    }

    @GetMapping("/{id}/metadata")
    public ResponseEntity<FileEntity> getMetadata(@PathVariable Long id) {
        Optional<FileEntity> optionalFile = fileRepository.findById(id);
        return optionalFile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable Long id) {
        Optional<FileEntity> optionalFile = fileRepository.findById(id);
        if (optionalFile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        fileRepository.deleteById(id);
        return ResponseEntity.ok("File deleted successfully");
    }

    @GetMapping("/all")
    public List<FileEntity> getAllFiles() {
        return fileRepository.findAll();
    }
}
