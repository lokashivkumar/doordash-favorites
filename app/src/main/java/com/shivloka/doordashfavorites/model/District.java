package com.shivloka.doordashfavorites.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by shiv.loka on 12/23/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class District {

    private String shortName;
    private int firstDeliveryPrice;
    private int id;
    private String name;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getFirstDeliveryPrice() {
        return firstDeliveryPrice;
    }

    public void setFirstDeliveryPrice(int firstDeliveryPrice) {
        this.firstDeliveryPrice = firstDeliveryPrice;
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
}
