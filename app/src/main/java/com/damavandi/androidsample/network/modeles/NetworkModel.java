package com.damavandi.androidsample.network.modeles;

/**
 * Created by arch on 1/3/17.
 */

public class NetworkModel {

    private int id;
    private String name;
    private CountryModel country;

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

    public CountryModel getCountry() {
        return country;
    }

    public void setCountry(CountryModel country) {
        this.country = country;
    }
}
