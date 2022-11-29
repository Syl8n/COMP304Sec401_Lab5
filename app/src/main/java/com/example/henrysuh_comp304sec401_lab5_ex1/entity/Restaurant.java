package com.example.henrysuh_comp304sec401_lab5_ex1.entity;

public class Restaurant {
    private int id;
    private String category;
    private String address;
    private String name;
    private String phone;
    private String priceRange;
    private double latitude;
    private double longitude;

    public Restaurant(int id, String category, String address, String name, String phone, String priceRange, double latitude, double longitude) {
        this.id = id;
        this.category = category;
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.priceRange = priceRange;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDetail() {
        return address + '\n' +
                "Price range: " + (priceRange != null ? priceRange : "Unknown") + '\n' +
                phone;
    }
}
