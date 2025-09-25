package com.example.filemanager.repository;

import com.example.filemanager.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findAllByExpireTimeBefore(LocalDateTime time);
}
