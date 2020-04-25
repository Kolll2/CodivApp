package com.kolll.datafetch.model;

public class GlobalData {
    private long recovered;
    private long cases;
    private long deaths;

    public long getRecovered() {
        return recovered;
    }

    public void setRecovered(long recovered) {
        this.recovered = recovered;
    }

    public long getCases() {
        return cases;
    }

    public void setCases(long cases) {
        this.cases = cases;
    }

    public long getDeath() {
        return deaths;
    }

    public void setDeath(long deaths) {
        this.deaths = deaths;
    }

    @Override
    public String toString() {
        return "GlobalData{" +
                "recovered=" + recovered +
                ", cases=" + cases +
                ", deaths=" + deaths +
                '}';
    }
}
