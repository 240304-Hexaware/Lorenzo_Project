package com.revature.entity;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "metadata")
public class Metadata {
    @Id
    private String id;
    private String userId;
    private String parsedDataId;
    private String flatFilePath;
    private Date uploadDate;
    private String specId;

    public Metadata() {
    }

    public Metadata(String id, String userId, String flatFilePath, String specId, String parsedDataId) {
        this.id = id;
        this.userId = userId;
        this.flatFilePath = flatFilePath;
        this.uploadDate = new Date();
        this.specId = specId;
        this.parsedDataId = parsedDataId;
    }

    public Metadata(String userId, String flatFilePath, String specId, String parsedDataId) {
        this.userId = userId;
        this.flatFilePath = flatFilePath;
        this.uploadDate = new Date();
        this.specId = specId;
        this.parsedDataId = parsedDataId;
    }

    public Metadata(String id, String userId, String flatFilePath, Date uploadDate, String specId, String parsedDataId) {
        this.id = id;
        this.userId = userId;
        this.flatFilePath = flatFilePath;
        this.uploadDate = uploadDate;
        this.specId = specId;
        this.parsedDataId = parsedDataId;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getFlatFilePath() {
        return flatFilePath;
    }

    public String getParsedDataId() {
        return parsedDataId;
    }

    public String getSpecId() {
        return specId;
    }

    public Date getUploadDate() {
        return uploadDate;
    }
}
