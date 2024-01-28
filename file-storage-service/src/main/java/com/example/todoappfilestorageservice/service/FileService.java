package com.example.todoappfilestorageservice.service;

import com.example.todoappfilestorageservice.entity.FileEntity;
import com.example.todoappfilestorageservice.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
public class FileService {

    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public String upload(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return "The file you submitted is empty. Please submit a valid file.";
            }

            FileEntity fileEntity = new FileEntity();
            fileEntity.setFileName(file.getOriginalFilename());
            fileEntity.setFile(file.getBytes());
            fileEntity.setUploadDate(new Date());
            this.save(fileEntity);

            return "File uploaded successfully!";
        } catch (IOException e) {
            return "Error while processing the file: " + e.getMessage();
        }
    }

    public ResponseEntity<byte[]> download(Integer fileId) {
        Optional<FileEntity> optionalFile = this.getFileById(fileId);

        if (optionalFile.isPresent()) {
            FileEntity file = optionalFile.get();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", file.getFileName());
            return new ResponseEntity<>(file.getFile(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Iterable<FileEntity> getAll() {
        return fileRepository.findAll();
    }

    public void save(FileEntity p) {
        fileRepository.save(p);
    }

    public void deleteFile(Integer fileId) {
        fileRepository.deleteById(fileId);
    }

    public Optional<FileEntity> getFileById(Integer fileId) {
        return fileRepository.findById(fileId);
    }
}
