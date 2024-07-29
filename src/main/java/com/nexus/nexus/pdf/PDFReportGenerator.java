package com.nexus.nexus.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.nexus.nexus.queue.Task;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PDFReportGenerator {

    public byte[] generateTaskReport(List<Task> tasks) throws DocumentException, IOException {
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();

        // Adiciona uma página ao documento
        document.add(new Paragraph("Suas atividades do dia"));

        // Adiciona detalhes das tarefas ao documento
        for (Task task : tasks) {
            document.add(new Paragraph("Tarefa: " + task.getName()));
        }

        document.close();

        return byteArrayOutputStream.toByteArray();
    }
}