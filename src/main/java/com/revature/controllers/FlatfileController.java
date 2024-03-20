package com.revature.controllers;

import com.revature.services.FlatfileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/flatfile")
public class FlatfileController {

    private final FlatfileService flatfileService;

    public FlatfileController(FlatfileService flatfileService){
        this.flatfileService = flatfileService;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return flatfileService.uploadFile(file);
    }

    @GetMapping
    public List<String> getAllFiles() {
//        List<String> files = new ArrayList<>();
//
//        String folderPath = "uploads";
//
//        // Get all files in the folder
//        File folder = new File(folderPath);
//        File[] listOfFiles = folder.listFiles();
//
//        if (listOfFiles != null) {
//            for (File file : listOfFiles) {
//                if (file.isFile()) {
//                    files.add(file.getName());
//                }
//            }
//        }
//
//        return files;
        return flatfileService.getAllFiles();
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String filename) throws IOException {
        return flatfileService.downloadFile(filename);
    }

}
