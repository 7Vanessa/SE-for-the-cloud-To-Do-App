package com.example.todoappfilestorageservice.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "file_entity")
public class FileEntity {

    @Id
    @Column(name = "fileId")
    private int fileId;

    @Lob
    @Column(name = "file", columnDefinition = "MEDIUMBLOB")
    private byte[] file;

    @Temporal(TemporalType.DATE)
    @Column(name = "uploadDate", nullable = false)
    private Date uploadDate;

    public Integer getFileId() {
        return fileId;
    }

    public byte[] getFile() { return file; }

    public void setFile(byte[] file) { this.file = file; }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public String toString() {
        return "ReportEntity{" +
                "fileId=" + fileId +
                ", file=<binary data>" +
                ", uploadDate=" + uploadDate +
                '}';
    }
}
