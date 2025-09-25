package com.example.filemanager.service;

import com.example.filemanager.repository.FileRepository;
import com.example.filemanager.model.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FileCleanupService {

    @Autowired
    private FileRepository fileRepository;

    @Scheduled(cron = "0 0 * * * *") // every hour
    public void deleteExpiredFiles() {
        List<FileEntity> expiredFiles = fileRepository.findAllByExpireTimeBefore(LocalDateTime.now());
        fileRepository.deleteAll(expiredFiles);
    }
}
