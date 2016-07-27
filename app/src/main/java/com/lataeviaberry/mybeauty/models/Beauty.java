package com.lataeviaberry.mybeauty.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Beauty {
    private String Name;
    private String Phone;
    private String Website;
    private double Rating;
    private String ImageUrl;
    private List<String> Address = new ArrayList<>();
    private double Latitude;
    private double Longitude;
    private List<String> Categories = new ArrayList<>();

    public Beauty() {}

    public Beauty(String name, String phone, String website,
                  double rating, String imageUrl, ArrayList<String> address,
                  double latitude, double longitude, ArrayList<String> categories) {
        this.Name = name;
        this.Phone = phone;
        this.Website = website;
        this.Rating = rating;
        ImageUrl = getLargeImageUrl(imageUrl);
        this.Address = address;
        this.Latitude = latitude;
        this.Longitude = longitude;
        this.Categories = categories;
    }
    public String getName() {
        return Name;
    }

    public String getPhone() {
        return Phone;
    }

    public String getWebsite() {
        return  Website;
    }

    public double getRating() {
        return Rating;
    }

    public String getImageUrl(){
        return ImageUrl;
    }

    public List<String> getAddress() {
        return Address;
    }

    public double getLatitude() {
        return Latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public List<String> getCategories() {
        return Categories;
    }
    public String getLargeImageUrl(String imageUrl) {
        String largeImageUrl = imageUrl.substring(0, imageUrl.length() - 6).concat("o.jpg");
        return largeImageUrl;
    }
}