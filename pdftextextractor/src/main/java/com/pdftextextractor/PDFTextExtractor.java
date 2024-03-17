package com.pdftextextractor;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PDFTextExtractor {

    public static void main(String[] args) {
        SpringApplication.run(PDFTextExtractor.class, args);
        System.out.println("Working");
    }

    @PostMapping("/process-pdf")
    public String processPdf(@RequestBody String pdfUrl) {
        // Path to your PDF file
        System.out.println("Processing PDF at URL: " + pdfUrl);

        try (PDDocument document =Loader.loadPDF(new File(pdfUrl))) {
            PDFTextStripper stripper = new PDFTextStripper();
            
            // Extract text from the PDF
            String text = stripper.getText(document);
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Haven't loaded PDF succesfully";
    }
}
