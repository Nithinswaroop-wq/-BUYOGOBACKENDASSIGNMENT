package com.example.BuyogoBackendAssignment.DTO;

import java.time.Instant;

public class filterResponsedto {
    private String machineid;
    private Instant start;
    private Instant end;
    private int eventscount=0;
    private int defectscount=0;
    private double avgdefectrate;
    private String status;

    public String getMachineid() {
        return machineid;
    }

    public void setMachineid(String machineid) {
        this.machineid = machineid;
    }

    public Instant getStart() {
        return start;
    }

    public void setStart(Instant start) {
        this.start = start;
    }

    public Instant getEnd() {
        return end;
    }

    public void setEnd(Instant end) {
        this.end = end;
    }

    public int getEventscount() {
        return eventscount;
    }

    public void setEventscount(int eventscount) {
        this.eventscount = eventscount;
    }

    public int getDefectscount() {
        return defectscount;
    }

    public void setDefectscount(int defectscount) {
        this.defectscount = defectscount;
    }


    public double getAvgdefectrate() {
        return avgdefectrate;
    }

    public void setAvgdefectrate(double avgdefectrate) {
        this.avgdefectrate = avgdefectrate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
