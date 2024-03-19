package com.revature.controllers;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/upload")
public class FlatfileUploadController {

    @PostMapping("/file")
    public String uploadFile(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return "Please select a file to upload.";
        }
        try {
            // Get the file name
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            // Create a directory to store files if it doesn't exist
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
}
