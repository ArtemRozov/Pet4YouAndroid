package com.example.petforyou;

import java.util.Date;

public class Advertisement {
    private int id;
    private String title;
    private String type;
    private String publicationDate;
    private int userId;
    private String description;
    private String petType;
    private boolean completed;

    private AdvertisementLocationModel advertisementLocation;
    private AdvertisementInfoModel advertisementInfo;

    public Advertisement(int id, String title, String type, String publicationDate, int userId, String description, String petType, boolean completed) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.publicationDate = publicationDate;
        this.userId = userId;
        this.description = description;
        this.petType = petType;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public AdvertisementLocationModel getAdvertisementLocation() {
        return advertisementLocation;
    }

    public void setAdvertisementLocation(AdvertisementLocationModel advertisementLocation) {
        this.advertisementLocation = advertisementLocation;
    }

    public AdvertisementInfoModel getAdvertisementInfo() {
        return advertisementInfo;
    }

    public void setAdvertisementInfo(AdvertisementInfoModel advertisementInfo) {
        this.advertisementInfo = advertisementInfo;
    }

}
