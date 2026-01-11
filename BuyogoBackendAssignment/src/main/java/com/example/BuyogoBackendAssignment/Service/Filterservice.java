package com.example.BuyogoBackendAssignment.Service;

import com.example.BuyogoBackendAssignment.DTO.Eventsdto;
import com.example.BuyogoBackendAssignment.DTO.Filtersdto;
import com.example.BuyogoBackendAssignment.DTO.filterResponsedto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class Filterservice {

    @Autowired
    private Eventsservice eventsservice;

    public filterResponsedto getstats(Filtersdto filtersdto) {

        filterResponsedto responsedto2 = new filterResponsedto();
        ConcurrentHashMap<String, Eventsdto> store = eventsservice.getStore();
        for (Eventsdto i : store.values())
        {
            if (!i.getMachineid().equals(filtersdto.getMachineid()))
            {
                continue;
            }
            if (i.getEventtime().isBefore(filtersdto.getStart()))//inclusive
            {
                continue;
            }
            if (!i.getEventtime().isBefore(filtersdto.getEnd()))//exclusive
            {
                continue;
            }

           //validating event and incrementing
            responsedto2.setEventscount(responsedto2.getEventscount() + 1);
            //counting defects
            if (i.getDefectcount() != -1) {
                responsedto2.setDefectscount(responsedto2.getDefectscount() + i.getDefectcount());
            }
        }

        //calculations based on conditions
        long seconds = Duration.between(filtersdto.getStart(), filtersdto.getEnd()).getSeconds();
        double windowhours = seconds / 3600.0;
        double avgRate;
        if (windowhours == 0)
        {
            avgRate = 0;
        }
        else
        {
            avgRate = responsedto2.getDefectscount() / windowhours;
        }
        String status;
        if (avgRate < 2.0)
        {
            status = "healthy";
        } else
        {
            status = "warning";
        }
        responsedto2.setAvgdefectrate(avgRate); //setting response
        responsedto2.setStatus(status);
        responsedto2.setMachineid(filtersdto.getMachineid());
        responsedto2.setStart(filtersdto.getStart());
        responsedto2.setEnd(filtersdto.getEnd());
         return responsedto2;
    }
}
