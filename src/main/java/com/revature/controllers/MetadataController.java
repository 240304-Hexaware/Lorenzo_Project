package com.revature.controllers;

import com.revature.entity.Metadata;
import com.revature.entity.ParsedData;
import com.revature.repositories.MetadataRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metadata")
public class MetadataController {

    private final MetadataRepository metadataRepository;

    public MetadataController(MetadataRepository metadataRepository){
        this.metadataRepository = metadataRepository;
    }


    @GetMapping("/{id}")
    public Metadata getMetadata(@PathVariable String id) throws Exception{
        return metadataRepository.findById(id)
                .orElseThrow(() -> new Exception("Id not found"));
    }

//    @GetMapping("/parsed/{userId}/{specId}")
//    public List<ParsedData> getParsedFromMeta(@PathVariable String userId, @PathVariable String specId) throws Exception{
//        List<ParsedData> parsedDataList = metadataRepository.findByUserIdAndSpecId(userId, specId);
//
//        if (parsedDataList.isEmpty()) {
//            throw new ChangeSetPersister.NotFoundException();
//        }
//
//        return parsedDataList;
//    }

}
