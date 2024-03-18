package com.revature.services;

import com.revature.controllers.SpecificationRequest;
import com.revature.entity.Field;
import com.revature.entity.Metadata;
import com.revature.entity.ParsedData;
import com.revature.entity.Specification;
import com.revature.repositories.MetadataRepository;
import com.revature.repositories.ParsedDataRepository;
import com.revature.repositories.SpecificationRepository;
import com.revature.util.FileParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service
public class ParsedDataService {

    private ParsedDataRepository parsedDataRepository;
    private final SpecificationRepository specificationRepository;
    private final MetadataRepository metadataRepository;


    @Autowired
    public ParsedDataService (ParsedDataRepository parsedDataRepository, SpecificationRepository specificationRepository, MetadataRepository metadataRepository){
        this.parsedDataRepository = parsedDataRepository;
        this.specificationRepository = specificationRepository;
        this.metadataRepository = metadataRepository;
    }

    public Metadata parse(SpecificationRequest request) throws Exception{
        String specificationId = request.getSpecificationId();
        Specification specification = specificationRepository.findById(specificationId)
                .orElseThrow(() -> new Exception("Specification not found"));

        String path = "/Users/user/IdeaProjects/Lorenzo_Project/flatfile.txt";

        File flatFile = new File(path); // Assuming flat file path

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
        ParsedData dataToBeParsed = new ParsedData(jsonObject.toMap());
        ParsedData parsedData = parsedDataRepository.save(dataToBeParsed);
        Metadata metadata = new Metadata(specification.getUserId(), path, specificationId, parsedData.getId());

        return metadataRepository.save(metadata);
    }

}
