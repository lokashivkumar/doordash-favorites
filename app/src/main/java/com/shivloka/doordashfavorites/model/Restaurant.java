package com.shivloka.doordashfavorites.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by shiv.loka on 12/23/16.
 */

//TODO: Make restaurant parcelable
    //TODO: Write tests for restaurantactivity
    //TODO: Write functional tests.

@JsonIgnoreProperties(ignoreUnknown = true)
public class Restaurant implements Parcelable {

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

    protected Restaurant(Parcel in) {
        id = in.readInt();
        name = in.readString();
        coverImageUrl = in.readString();
        deliveryFee = in.readDouble();
        cuisineType = in.readString();
        deliveryTime = in.readString();
        isUserFavorite = in.readByte() != 0;
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

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
                "deliveryTime='" + deliveryTime + '\'' +
                ", deliveryFee=" + deliveryFee +
                ", coverImageUrl='" + coverImageUrl + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(coverImageUrl);
        dest.writeDouble(deliveryFee);
        dest.writeString(cuisineType);
        dest.writeString(deliveryTime);
        dest.writeByte((byte) (isUserFavorite ? 1 : 0));
    }
}
