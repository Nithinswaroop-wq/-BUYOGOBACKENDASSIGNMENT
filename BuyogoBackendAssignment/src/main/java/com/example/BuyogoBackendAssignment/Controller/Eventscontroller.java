package com.example.BuyogoBackendAssignment.Controller;

import com.example.BuyogoBackendAssignment.DTO.*;
import com.example.BuyogoBackendAssignment.Service.Eventsservice;
import com.example.BuyogoBackendAssignment.Service.Filterservice;
import com.example.BuyogoBackendAssignment.Service.Topdefectlinesservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/events")

public class Eventscontroller {
    @Autowired
    private Eventsservice eventsservice;
    @Autowired
    private Filterservice filterservice;
    @Autowired
    private Topdefectlinesservice topdefectlinesservice;
    @PostMapping("/batch")
    public Responsedto batch(@RequestBody List<Eventsdto> eventsdto)
    {
        return eventsservice.eventservicelogic(eventsdto);
    }
    @PostMapping("/stats")
    public filterResponsedto stats(@RequestBody Filtersdto filtersdto)
    {
       return filterservice.getstats(filtersdto);
    }
    @PostMapping("/topdefectlines")
    public List<Topdefectsdto> topDefectLines(@RequestBody Topdefectsrequestdto dto) {
        return topdefectlinesservice.getTopDefectLines( dto.getFactoryId(), dto.getFrom(), dto.getTo(), dto.getLimit());

    }

}
