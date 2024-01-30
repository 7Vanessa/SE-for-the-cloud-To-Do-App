package com.example.todoappfilestorageservice.controller;

import com.example.todoappfilestorageservice.entity.FileEntity;
import com.example.todoappfilestorageservice.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "File Storage", description = "Spring Boot API for file storage")
@RestController
@RequestMapping(path = "/api/files")
public class MainController {
    @Autowired
    private FileService fileService;

    @Operation(
            summary = "Upload a file",
            description = "Upload a file from your local machine. The response is a String.",
            tags = { "upload", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = FileService.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @PostMapping(path="/upload")
    public @ResponseBody String uploadReport (@RequestParam("file")MultipartFile file){
        return fileService.upload(file);
    }

    @Operation(
            summary = "Download a file by ID",
            description = "Download a file in your local machine by giving its ID. The response is a File.",
            tags = { "download", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = FileService.class), mediaType = "application/pdf") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/download/{fileId}")
    public ResponseEntity<byte[]> downloadReport(@PathVariable Integer fileId) {
        return fileService.download(fileId);
    }

    @Operation(
            summary = "Display all the uploaded files",
            description = "Display all the uploaded previous files. The response is a list of Files.",
            tags = { "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/all")
    public @ResponseBody Iterable<FileEntity> getAllFiles() {
        return fileService.getAll();
    }

    @Operation(
            summary = "Delete a file by ID",
            description = "Delete a file in the list of uploaded files by giving its ID.",
            tags = { "delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @DeleteMapping("/{fileId}")
    public ResponseEntity<String> deleteFile(@PathVariable Integer fileId) {
        fileService.deleteFile(fileId);
        return ResponseEntity.ok("File deleted successfully");
    }
}
