package com.example.todoappfilestorageservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

public class SubmitBody {

    @JsonProperty("file")
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

}
