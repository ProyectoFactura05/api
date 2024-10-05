package org.izpo.apigestionfacturas.controller;


import org.izpo.apigestionfacturas.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/users")
    public ResponseEntity<String> generateUserReport(@RequestParam("filePath") String filePath) {
        return reportService.generateReport(filePath);
    }
}
