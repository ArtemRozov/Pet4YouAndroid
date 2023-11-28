package com.example.petforyou;

public class AdvertisementFilterModel {
    private String advertisementType;
    private String[] petTypes;
    private String[] petBreeds;
    private AdvertisementLocationModel location;
    private String gender;
    private int minAge;
    private int maxAge;
    private int minPrice;
    private  int maxPrice;

    public AdvertisementFilterModel(String advertisementType, String[] petTypes, String[] petBreeds, AdvertisementLocationModel location, String gender, int minAge, int maxAge, int minPrice, int maxPrice) {
        this.advertisementType = advertisementType;
        this.petTypes = petTypes;
        this.petBreeds = petBreeds;
        this.location = location;
        this.gender = gender;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public String getAdvertisementType() {
        return advertisementType;
    }

    public void setAdvertisementType(String advertisementType) {
        this.advertisementType = advertisementType;
    }

    public String[] getPetTypes() {
        return petTypes;
    }

    public void setPetTypes(String[] petTypes) {
        this.petTypes = petTypes;
    }

    public String[] getPetBreeds() {
        return petBreeds;
    }

    public void setPetBreeds(String[] petBreeds) {
        this.petBreeds = petBreeds;
    }

    public AdvertisementLocationModel getLocation() {
        return location;
    }

    public void setLocation(AdvertisementLocationModel location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }
}
