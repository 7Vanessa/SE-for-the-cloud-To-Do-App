package com.example.todoappfilestorageservice.service;

import com.example.todoappfilestorageservice.domain.SubmitBody;
import com.example.todoappfilestorageservice.entity.FileEntity;
import com.example.todoappfilestorageservice.repository.FileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
public  class FileService {
    @Autowired
    FileRepository fileRepository;


    @Transactional
    public @ResponseBody String upload(MultipartFile file){

        try {
            if (file.isEmpty()) {
                return "The file you submitted is empty. Please submit a valid file.";
            }

            FileEntity fileEntity = new FileEntity();
            fileEntity.setFile(file.getBytes());
            fileEntity.setUploadDate(new Date());
            System.out.println(file);
            this.save(fileEntity);
//
            return "File uploaded successfully!";
        } catch (IOException e) {
            return "Error while processing the file: " + e.getMessage();
        }
    }

    @Transactional
    public ResponseEntity<byte[]> download(Integer fileId){

        Optional<FileEntity> optionalFile = this.getFileById(fileId);

        if (optionalFile.isPresent()) {
            FileEntity file = optionalFile.get();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "file.pdf");
            return new ResponseEntity<>(file.getFile(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

/*
    @Transactional
    public @ResponseBody String validate(Integer studentId){

        try {
            Optional<FileEntity> optionalReport = this.getReportById(studentId);

            if (optionalReport.isPresent()) {
                FileEntity report = optionalReport.get();
                Integer teacherVote = report.getTeacherVote();
                Integer tutorVote = report.getTutorVote();
                report.getTutorVote();

                if (teacherVote == 1 && tutorVote == 1) {
                    // Implement the logic
                    return "Report validated!";
                } else {
                    // Implement the logic
                    return "Report not validated.";
                }
            } else {
                return "Report not found for studentId: " + studentId;
            }
        } catch (Exception e) {
            return "Error updating report status: " + e.getMessage();
        }
    }

 */
/*
    @Transactional
    public @ResponseBody String submitVote(Integer studentId, String role, Integer vote){

        try {
            Optional<FileEntity> optionalReport = this.getReportById(studentId);

            if (optionalReport.isPresent()) {
                FileEntity report = optionalReport.get();
                if(Objects.equals(role, "teacher")){
                    report.setTeacherVote(vote);
                    this.save(report);
                }
                else if(Objects.equals(role, "tutor")){
                    report.setTutorVote(vote);
                    this.save(report);

                }
                else{
                    return "wrong role has been sent";
                }
                return role +" vote successfully submitted!";



            } else {
                return "Report not found for studentId: " + studentId;
            }
        } catch (Exception e) {
            return "Error submitting teacher vote: " + e.getMessage();
        }

    }

 */

    @Transactional
    public Iterable<FileEntity> getAll(){
        return fileRepository.findAll();
    }

    @Transactional
    public void save(FileEntity p){
        fileRepository.save(p);
    }

    @Transactional
    public Optional<FileEntity> getFileById(Integer fileId) { return fileRepository.findById(fileId); }

}
