package com.revature.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "parsed_data")
public class ParsedData {

    @Id
    private String id;
    private Object parsedData;

    public ParsedData() {
    }

    public ParsedData(Object parsedData) {
        this.parsedData = parsedData;
    }

    public String getId() {
        return id;
    }


    public Object getParsedData() {
        return parsedData;
    }
}
