package com.revature.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "parsed_data")
public class ParsedData {

    @Id
    private String id;
    private String metaId;
    private String parsedData;

    public ParsedData() {
    }

    public ParsedData(String id, String metaId, String parsedData) {
        this.id = id;
        this.metaId = metaId;
        this.parsedData = parsedData;
    }

    public String getId() {
        return id;
    }

    public String getMetaId() {
        return metaId;
    }

    public String getParsedData() {
        return parsedData;
    }
}
