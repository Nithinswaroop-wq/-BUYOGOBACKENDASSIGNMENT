package com.example.BuyogoBackendAssignment.DTO;

import java.time.Instant;

public class Eventsdto {
    private String eventid;
    private Instant eventtime;
    private Instant receivedtime;
    private String machineid;
    private long durationms;
    private int defectcount;
    private String factoryid;
    private String lineid;

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }


    public Instant getEventtime() {
        return eventtime;
    }

    public void setEventtime(Instant eventtime) {
        this.eventtime = eventtime;
    }

    public Instant getReceivedtime() {
        return receivedtime;
    }

    public void setReceivedtime(Instant receivedtime) {
        this.receivedtime = receivedtime;
    }

    public String getMachineid() {
        return machineid;
    }

    public void setMachineid(String machineid) {
        this.machineid = machineid;
    }

    public long getDurationms() {
        return durationms;
    }

    public void setDurationms(long durationms) {
        this.durationms = durationms;
    }

    public int getDefectcount() {
        return defectcount;
    }

    public void setDefectcount(int defectcount) {
        this.defectcount = defectcount;
    }

    public String getFactoryid() {
        return factoryid;
    }

    public void setFactoryid(String factoryid) {
        this.factoryid = factoryid;
    }

    public String getLineid() {
        return lineid;
    }

    public void setLineid(String lineid) {
        this.lineid = lineid;
    }
}
