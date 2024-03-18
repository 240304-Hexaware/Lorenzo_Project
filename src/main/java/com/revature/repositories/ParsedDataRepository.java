package com.revature.repositories;

import com.revature.entity.ParsedData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParsedDataRepository extends MongoRepository<ParsedData, String> {

}