package com.example.BuyogoBackendAssignment.DTO;

import java.util.ArrayList;
import java.util.List;

public class Responsedto {
    private int accepted=0;
    private int deduped=0;
    private int updated=0;
    private int rejected=0;
    private List<Rejectiondto> rejections=new ArrayList<>();

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public int getDeduped() {
        return deduped;
    }

    public void setDeduped(int deduped) {
        this.deduped = deduped;
    }

    public int getUpdated() {
        return updated;
    }

    public void setUpdated(int updated) {
        this.updated = updated;
    }

    public int getRejected() {
        return rejected;
    }

    public void setRejected(int rejected) {
        this.rejected = rejected;
    }

    public List<Rejectiondto> getRejections() {
        return rejections;
    }

    public void setRejections(List<Rejectiondto> rejections) {
        this.rejections = rejections;
    }
}
