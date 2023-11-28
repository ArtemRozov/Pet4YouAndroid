package com.example.petforyou;

public class AdvertisementInfoModel {
    private int id;
    private String breed;
    private int age;
    private double price;
    private String gender;

    public AdvertisementInfoModel(int id, String breed, int age, double price, String gender) {
        this.id = id;
        this.breed = breed;
        this.age = age;
        this.price = price;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
