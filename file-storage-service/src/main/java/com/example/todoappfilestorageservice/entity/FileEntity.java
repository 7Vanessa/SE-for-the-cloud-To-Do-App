package com.example.todoappfilestorageservice.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "file_entity")
public class FileEntity {

    @Id
    @Column(name = "fileId")
    private int fileId;

    @Column(name = "fileName", nullable = false)
    private String fileName;

    @Lob
    @Column(name = "file", columnDefinition = "MEDIUMBLOB")
    private byte[] file;

    @Temporal(TemporalType.DATE)
    @Column(name = "uploadDate", nullable = false)
    private Date uploadDate;

    // Getters and setters for fileName
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public String toString() {
        return "FileEntity{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", file=<binary data>" +
                ", uploadDate=" + uploadDate +
                '}';
    }
}
