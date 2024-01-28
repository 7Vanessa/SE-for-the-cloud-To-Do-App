package com.example.studentreportwebservice.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "report")
public class ReportEntity {

    @Id
    @Column(name = "studentId")
    private int studentId;

    @Lob
    @Column(name = "file", columnDefinition = "MEDIUMBLOB")
    private byte[] file;

    @Column(name = "teacherId")
    private int teacherId;

    @Column(name = "teacherVote")
    private int teacherVote;

    @Column(name = "tutorId")
    private int tutorId;

    @Column(name = "tutorVote")
    private int tutorVote;

    @Temporal(TemporalType.DATE)
    @Column(name = "uploadDate", nullable = false)
    private Date uploadDate;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public byte[] getFile() { return file; }

    public void setFile(byte[] file) { this.file = file; }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getTeacherVote() {
        return teacherVote;
    }

    public void setTeacherVote(Integer teacherVote) {
        this.teacherVote = teacherVote;
    }

    public Integer getTutorId() {
        return tutorId;
    }

    public void setTutorId(Integer tutorId) {
        this.tutorId = tutorId;
    }

    public Integer getTutorVote() {
        return tutorVote;
    }

    public void setTutorVote(Integer tutorVote) {
        this.tutorVote = tutorVote;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public String toString() {
        return "ReportEntity{" +
                "studentId=" + studentId +
                ", file=<binary data>" +
                ", teacherId=" + teacherId +
                ", teacherVote=" + teacherVote +
                ", tutorId=" + tutorId +
                ", tutorVote=" + tutorVote +
                ", uploadDate=" + uploadDate +
                '}';
    }
}
