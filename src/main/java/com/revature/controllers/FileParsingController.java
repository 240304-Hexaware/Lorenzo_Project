package com.revature.controllers;

import com.revature.entity.Field;
import com.revature.entity.Metadata;
import com.revature.entity.ParsedData;
import com.revature.entity.Specification;
import com.revature.repositories.ParsedDataRepository;
import com.revature.repositories.SpecificationRepository;
import com.revature.services.ParsedDataService;
import com.revature.services.SpecificationService;
import com.revature.util.FileParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.query.Meta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/parse")
public class FileParsingController {

    private final ParsedDataService parsedDataService;

    @Autowired
    public FileParsingController(ParsedDataService parsedDataService) {
        this.parsedDataService = parsedDataService;
    }

    @PostMapping
    public ParsedData parseFile(@RequestBody SpecificationRequest request) throws Exception {
        return parsedDataService.parse(request);
    }

    @GetMapping("/{parsedId}")
    public ParsedData getParsedData(@PathVariable String parsedId) throws Exception{
        return parsedDataService.getParsedData(parsedId);
    }
}
