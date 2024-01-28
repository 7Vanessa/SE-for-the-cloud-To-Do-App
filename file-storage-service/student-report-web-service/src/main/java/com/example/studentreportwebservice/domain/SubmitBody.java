package com.example.studentreportwebservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

public class SubmitBody {

    @JsonProperty("studentId")
    private Integer studentId;

    @JsonProperty("file")
    private MultipartFile file;

    @JsonProperty("tutorId")
    private Integer teacherId;

    @JsonProperty("tutorId")
    private Integer tutorId;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getTutorId() {
        return tutorId;
    }

    public void setTutorId(Integer tutorId) {
        this.tutorId = tutorId;
    }
}
