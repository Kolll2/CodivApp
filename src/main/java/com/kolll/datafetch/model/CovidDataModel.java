package com.kolll.datafetch.model;

public class CovidDataModel {
    private GlobalData globalData;
    private CountryData countryData;

    public CovidDataModel(GlobalData globalData, CountryData countryData) {
        this.globalData = globalData;
        this.countryData = countryData;
    }

    public void setGlobalData(GlobalData globalData) {
        this.globalData = globalData;
    }

    public void setCountryData(CountryData countryData) {
        this.countryData = countryData;
    }

    @Override
    public String toString() {
        return "CovidDataModel{ " +
                "\n" + globalData +
                "\n" + countryData +
                '}';
    }
}
