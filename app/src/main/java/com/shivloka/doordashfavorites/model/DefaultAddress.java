package com.shivloka.doordashfavorites.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by shiv.loka on 12/23/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DefaultAddress {

    @JsonProperty("district")
    private District district;
    private String city;
    private double manualLng;
    private String subpremise;
    private String objectType;
    private String street;
    private long id;
    private String printableAddress;
    private String state;
    private String shortName;
    private String submarket;
    private double lat;
    private String driverInstructions;
    private double lng;
    private int submarketId;
    private double manualLat;
    private String market;
    private long zipCode;

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getManualLng() {
        return manualLng;
    }

    public void setManualLng(double manualLng) {
        this.manualLng = manualLng;
    }

    public String getSubpremise() {
        return subpremise;
    }

    public void setSubpremise(String subpremise) {
        this.subpremise = subpremise;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPrintableAddress() {
        return printableAddress;
    }

    public void setPrintableAddress(String printableAddress) {
        this.printableAddress = printableAddress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSubmarket() {
        return submarket;
    }

    public void setSubmarket(String submarket) {
        this.submarket = submarket;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getDriverInstructions() {
        return driverInstructions;
    }

    public void setDriverInstructions(String driverInstructions) {
        this.driverInstructions = driverInstructions;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getSubmarketId() {
        return submarketId;
    }

    public void setSubmarketId(int submarketId) {
        this.submarketId = submarketId;
    }

    public double getManualLat() {
        return manualLat;
    }

    public void setManualLat(double manualLat) {
        this.manualLat = manualLat;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public long getZipCode() {
        return zipCode;
    }

    public void setZipCode(long zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "DefaultAddress{" +
                "city='" + city + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", zipCode=" + zipCode +
                '}';
    }
}
