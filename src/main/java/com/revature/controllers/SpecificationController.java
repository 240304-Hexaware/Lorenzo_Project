package com.revature.controllers;

import com.revature.entity.Field;
import com.revature.entity.Specification;
import com.revature.repositories.SpecificationRepository;
import com.revature.services.SpecificationService;
import com.revature.util.FileParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/specifications")
public class SpecificationController {

    private final SpecificationService specificationService;

    @Autowired
    public SpecificationController(SpecificationService specificationService) {
        this.specificationService = specificationService;
    }

    @PostMapping
    public ResponseEntity<Specification> postSpecification(@RequestBody Specification specification) {
        return specificationService.createSpecification(specification);
    }

    @GetMapping("/{specName}")
    public Specification getSpecification(@PathVariable String specName) throws Exception{
        return specificationService.findByName(specName);
    }
}
