package com.revature.repositories;

import com.revature.entity.Metadata;
import com.revature.entity.ParsedData;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MetadataRepository extends MongoRepository<Metadata, String> {

}
