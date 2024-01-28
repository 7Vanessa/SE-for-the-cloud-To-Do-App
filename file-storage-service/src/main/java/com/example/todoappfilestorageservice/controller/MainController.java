package com.example.todoappfilestorageservice.controller;

import com.example.todoappfilestorageservice.entity.FileEntity;
import com.example.todoappfilestorageservice.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path = "/api/files")
public class MainController {
    @Autowired
    private FileService fileService;

    @PostMapping(path="/upload")
    public @ResponseBody String uploadReport (@RequestParam("file")MultipartFile file){
        return fileService.upload(file);
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<byte[]> downloadReport(@PathVariable Integer fileId) {
        return fileService.download(fileId);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<FileEntity> getAllFiles() {
        return fileService.getAll();
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<String> deleteFile(@PathVariable Integer fileId) {
        fileService.deleteFile(fileId);
        return ResponseEntity.ok("File deleted successfully");
    }
}
