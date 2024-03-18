package com.revature.controllers;

import com.revature.entity.Field;
import com.revature.entity.Specification;
import com.revature.repositories.SpecificationRepository;
import com.revature.util.FileParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/file")
public class FileParsingController {

    private SpecificationRepository specificationRepository; // Assuming you have a repository for specifications

    @Autowired
    public FileParsingController(SpecificationRepository specificationRepository) {
        this.specificationRepository = specificationRepository;
    }

    @PostMapping("/parse")
    public Map<String, Object> parseFile(@RequestBody SpecificationRequest request) throws Exception {
        String specificationId = request.getSpecificationId();
        Specification specification = specificationRepository.findById(specificationId)
                .orElseThrow(() -> new Exception("Specification not found"));

        File flatFile = new File("/Users/user/IdeaProjects/Lorenzo_Project/flatfile.txt"); // Assuming flat file path

        FileParser parser = new FileParser();
        String data = parser.readAllBytes(flatFile);
        Map<String, Field> specMap = specification.getSpecs(); // Assuming getSpecs() returns the specification map
        List<String> fieldValues = parser.readStringFields(data, specMap);

        JSONObject jsonObject = new JSONObject();
        int i = 0;
        for (String fieldName : specMap.keySet()) {
            jsonObject.put(fieldName, fieldValues.get(i));
            i++;
        }

        return jsonObject.toMap();
    }
}
