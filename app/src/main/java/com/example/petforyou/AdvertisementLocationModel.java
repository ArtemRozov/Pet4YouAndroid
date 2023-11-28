package com.example.petforyou;

public class AdvertisementLocationModel {
    private int id;
    private String country;
    private String city;
    private String region;

    public AdvertisementLocationModel(int id, String country, String city, String region) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.region = region;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
