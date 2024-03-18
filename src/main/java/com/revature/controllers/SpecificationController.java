package com.revature.controllers;

import com.revature.entity.Field;
import com.revature.entity.Specification;
import com.revature.repositories.SpecificationRepository;
import com.revature.util.FileParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/specifications")
public class SpecificationController {

    private final SpecificationRepository specificationRepository;

    @Autowired
    public SpecificationController(SpecificationRepository specificationRepository) {
        this.specificationRepository = specificationRepository;
    }

    @PostMapping
    public ResponseEntity<Specification> createSpecification(@RequestBody Specification specification) {
        try{
            Specification savedSpecification = specificationRepository.save(specification);
            return new ResponseEntity<>(savedSpecification, HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
