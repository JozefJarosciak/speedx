package com.mapbox.services.geocoding.v5.models;

import com.google.gson.annotations.SerializedName;

public class CarmenContext {
    private String category;
    private String id;
    private String maki;
    @SerializedName("short_code")
    private String shortCode;
    private String text;
    private String wikidata;

    public String getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }

    public String getShortCode() {
        return this.shortCode;
    }

    public String getWikidata() {
        return this.wikidata;
    }

    public String getCategory() {
        return this.category;
    }

    public String getMaki() {
        return this.maki;
    }
}
