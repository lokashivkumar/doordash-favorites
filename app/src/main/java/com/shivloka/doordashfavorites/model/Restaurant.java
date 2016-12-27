package com.shivloka.doordashfavorites.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by shiv.loka on 12/23/16.
 */

//TODO: Make restaurant parcelable
    //TODO: Write tests for restaurantactivity
    //TODO: Write functional tests.

@JsonIgnoreProperties(ignoreUnknown = true)
public class Restaurant {

    private int id;
    private String name;
    @JsonProperty("cover_img_url")
    private String coverImageUrl;
    @JsonProperty("delivery_fee")
    private double deliveryFee;
    @JsonProperty("description")
    private String cuisineType;
    @JsonProperty("status")
    private String deliveryTime;
    private boolean isUserFavorite;

    public Restaurant() {}

    public boolean isUserFavorite() {
        return isUserFavorite;
    }

    public void setUserFavorite(boolean userFavorite) {
        isUserFavorite = userFavorite;
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

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Override
    public String toString() {
        return "Restaurant: {" +
                "cuisineType= " +cuisineType +
                "deliveryTime= " +deliveryTime +
                ", deliveryFee= " +deliveryFee +
                ", coverImageUrl= " +coverImageUrl +
                ", name=" +name +
                ", isUserFavorite='" +isUserFavorite +
                "}";
    }
}
