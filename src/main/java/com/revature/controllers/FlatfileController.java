package com.revature.controllers;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/flatfile")
public class FlatfileController {

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return "Please select a file to upload.";
        }
        try {
            // Get the file name
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            File directory = new File("uploads");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save the file to the uploads directory
            Path filePath = Paths.get("uploads", fileName);
            Files.write(filePath, file.getBytes());

            return "File uploaded successfully: " + fileName;
        } catch (Exception e) {
            return "Failed to upload file: " + e.getMessage();
        }
    }

    @GetMapping
    public List<String> getAllFiles() {
        List<String> files = new ArrayList<>();

        String folderPath = "uploads";

        // Get all files in the folder
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    files.add(file.getName());
                }
            }
        }

        return files;
    }

}
