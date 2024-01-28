package com.example.todoappfilestorageservice.controller;

import com.example.todoappfilestorageservice.domain.SubmitBody;
import com.example.todoappfilestorageservice.entity.FileEntity;
import com.example.todoappfilestorageservice.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
//@RequestMapping(path = "/test")
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

    /*
    @PostMapping(path = "/validate/studentId")
    public @ResponseBody String validateReport(
            @PathVariable Integer studentId
    ) {
        return reportService.validate(studentId);


    }

     */

    /*
    @PostMapping("/vote/{role}/{studentId}")
    public @ResponseBody String submitTeacherVote(@PathVariable("studentId") Integer studentId,@PathVariable("role") String role, @RequestBody Integer vote){
        return reportService.submitVote(studentId, role, vote);
    }

     */

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
    public @ResponseBody Iterable<FileEntity> getAllFiles() {
        return fileService.getAll();
//        return reportRepository.findAll();
    }
}
