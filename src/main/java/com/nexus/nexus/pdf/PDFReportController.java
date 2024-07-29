package com.nexus.nexus.pdf;

import com.nexus.nexus.queue.Task;
import com.nexus.nexus.queue.TaskManager;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/reports")
public class PDFReportController {

    private static final Logger logger = Logger.getLogger(PDFReportController.class.getName());

    @Autowired
    private TaskManager taskManager;

    @Autowired
    private PDFReportGenerator pdfReportGenerator;

    @GetMapping("/tasks")
    public ResponseEntity<byte[]> generateTaskReport() {
        List<Task> tasks = taskManager.listTasks();
        logger.info("Gerando lista de tarefas: " + tasks);

        try {
            byte[] pdfBytes = pdfReportGenerator.generateTaskReport(tasks);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=task_report.pdf");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(pdfBytes);
        } catch (DocumentException | IOException e) {
            logger.severe("Erro gerando lista de tarefas: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}