package com.example.studentreportwebservice.controller;

import com.example.studentreportwebservice.domain.SubmitBody;
import com.example.studentreportwebservice.entity.ReportEntity;
import com.example.studentreportwebservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Controller
//@RequestMapping(path = "/test")
@RequestMapping(path = "/reports")
public class MainController {
    @Autowired
    private ReportService reportService;

    @PostMapping(path="/submit")
    public @ResponseBody String submitReport (@RequestBody SubmitBody body){

       return reportService.submit(body);

    }

    @GetMapping("/download/{studentId}")
    public ResponseEntity<byte[]> downloadReport(@PathVariable Integer studentId) {

        return reportService.download(studentId);
    }

    @PostMapping(path = "/validate/studentId")
    public @ResponseBody String validateReport(
            @PathVariable Integer studentId
    ) {
        return reportService.validate(studentId);


    }

    @PostMapping("/vote/{role}/{studentId}")
    public @ResponseBody String submitTeacherVote(@PathVariable("studentId") Integer studentId,@PathVariable("role") String role, @RequestBody Integer vote){
        return reportService.submitVote(studentId, role, vote);
    }

//    @PostMapping("/vote/tutor/{studentId}")
//    public @ResponseBody String submitTutorVote(@PathVariable Integer studentId, @RequestParam Integer tutorVote) {
//        try{
//            Optional<ReportEntity> optionalReport = reportService.getReportById(studentId);
//
//            if (optionalReport.isPresent()) {
//                ReportEntity report = optionalReport.get();
//                report.setTutorVote(tutorVote);
//                reportService.save(report);
//
//                return "Tutor vote successfully submitted!";
//            } else {
//                return "Report not found for studentId: " + studentId;
//            }
//        } catch (Exception e) {
//            return "Error submitting tutor vote: " + e.getMessage();
//        }
//    }
    /*
    @PostMapping(path="/add")
    public @ResponseBody String addNewReport (@RequestParam Integer studentId, @RequestParam byte[] file, @RequestParam Integer teacherId, @RequestParam Integer teacherVote, @RequestParam Integer tutorId, @RequestParam Integer tutorVote){
        ReportEntity p = new ReportEntity();
        p.setStudentId(studentId);
        p.setFile(file);
        p.setTeacherId(teacherId);
        p.setTeacherVote(teacherVote);
        p.setTutorId(tutorId);
        p.setTutorVote(tutorVote);
        System.out.println(p);
        reportService.save(p);
//        reportRepository.save(p);
        return "Saved";
    }*/

    @GetMapping("/all")
    public @ResponseBody Iterable<ReportEntity> getAllReports() {
        return reportService.getAll();
//        return reportRepository.findAll();
    }
}
