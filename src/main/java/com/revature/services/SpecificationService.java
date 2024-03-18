package com.revature.services;

import com.revature.entity.Specification;
import com.revature.repositories.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class SpecificationService {
    private SpecificationRepository specificationRepository;

    @Autowired
    public SpecificationService(SpecificationRepository specificationRepository){
        this.specificationRepository = specificationRepository;
    }

    public Specification findByName(String name) throws Exception {
        return specificationRepository.findBySpecName(name)
                .orElseThrow(() -> new Exception("Spec name could not be found" ));
    }

    public ResponseEntity<Specification> createSpecification(Specification specification) {
        try{
            Specification savedSpecification = specificationRepository.save(specification);
            return new ResponseEntity<>(savedSpecification, HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
