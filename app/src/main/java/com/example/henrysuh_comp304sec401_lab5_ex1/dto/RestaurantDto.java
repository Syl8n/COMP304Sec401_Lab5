package com.example.henrysuh_comp304sec401_lab5_ex1.dto;

public class RestaurantDto {
    private int id;
    private String name;
    private String priceRange;
    private double latitude;
    private double longitude;

    public RestaurantDto() {

    }

    public RestaurantDto(int id, String name, String priceRange, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.priceRange = priceRange;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return name + '\n' +
                "Price range: " + (priceRange != null ? priceRange : "Unknown");
    }
}
