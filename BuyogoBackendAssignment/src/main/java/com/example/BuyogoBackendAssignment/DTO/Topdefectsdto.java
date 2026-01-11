package com.example.BuyogoBackendAssignment.DTO;

public class Topdefectsdto {
    private String lineid;
    private int totaldefects;
    private int eventcount;
    private double defectspercent;

    public String getLineid() {
        return lineid;
    }

    public void setLineid(String lineid) {
        this.lineid = lineid;
    }

    public int getTotaldefects() {
        return totaldefects;
    }

    public void setTotaldefects(int totaldefects) {
        this.totaldefects = totaldefects;
    }

    public int getEventcount() {
        return eventcount;
    }

    public void setEventcount(int eventcount) {
        this.eventcount = eventcount;
    }

    public double getDefectspercent() {
        return defectspercent;
    }

    public void setDefectspercent(double defectspercent) {
        this.defectspercent = defectspercent;
    }
}
