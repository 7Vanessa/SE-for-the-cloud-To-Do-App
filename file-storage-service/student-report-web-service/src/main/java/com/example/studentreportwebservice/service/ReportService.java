package com.example.studentreportwebservice.service;

import com.example.studentreportwebservice.domain.SubmitBody;
import com.example.studentreportwebservice.entity.ReportEntity;
import com.example.studentreportwebservice.repository.ReportRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public  class ReportService {
    @Autowired
    ReportRepository reportRepository;


    @Transactional
    public @ResponseBody String submit(SubmitBody body){

        try {
            if (body.getFile().isEmpty()) {
                return "The file you submitted is empty. Please submit a valid file.";
            }

            ReportEntity report = new ReportEntity();
            report.setStudentId(body.getStudentId());
            report.setFile(body.getFile().getBytes());
            report.setTeacherId(body.getTeacherId());
            report.setTutorId(body.getTutorId());
            report.setUploadDate(new Date());
            System.out.println(report);
            this.save(report);
//        reportRepository.save(p);
            return "Report submitted successfully!";
        } catch (IOException e) {
            return "Error while processing the file: " + e.getMessage();
        }
    }

    @Transactional
    public ResponseEntity<byte[]> download(Integer studentId){

        Optional<ReportEntity> optionalReport = this.getReportById(studentId);

        if (optionalReport.isPresent()) {
            ReportEntity report = optionalReport.get();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "report.pdf");
            return new ResponseEntity<>(report.getFile(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @Transactional
    public @ResponseBody String validate(Integer studentId){

        try {
            Optional<ReportEntity> optionalReport = this.getReportById(studentId);

            if (optionalReport.isPresent()) {
                ReportEntity report = optionalReport.get();
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

    @Transactional
    public @ResponseBody String submitVote(Integer studentId, String role, Integer vote){

        try {
            Optional<ReportEntity> optionalReport = this.getReportById(studentId);

            if (optionalReport.isPresent()) {
                ReportEntity report = optionalReport.get();
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

    @Transactional
    public Iterable<ReportEntity> getAll(){
        return reportRepository.findAll();
    }

    @Transactional
    public void save(ReportEntity p){
        reportRepository.save(p);
    }

    @Transactional
    public Optional<ReportEntity> getReportById(Integer studentId) { return reportRepository.findById(studentId); }

}
