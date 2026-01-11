package com.example.BuyogoBackendAssignment.Service;

import com.example.BuyogoBackendAssignment.DTO.Eventsdto;
import com.example.BuyogoBackendAssignment.DTO.Topdefectsdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class Topdefectlinesservice {
    @Autowired
    private Eventsservice eventsservice;
    public List<Topdefectsdto> getTopDefectLines(String factoryId, Instant from, Instant to, int limit)
    {
        ConcurrentHashMap<String, Eventsdto> store = eventsservice.getStore();
        List<Topdefectsdto> result = new ArrayList<>();
        // filtering events
        for (Eventsdto e : store.values())
        {
            if (!e.getFactoryid().equals(factoryId))
            {
                continue;
            }
            if (e.getEventtime().isBefore(from))
            {
                continue;
            }
            if (!e.getEventtime().isBefore(to))
            {
                continue;
            }
            String lineid = e.getLineid();
            //  finding and creating line stats
            Topdefectsdto lineStats = null;
            for (Topdefectsdto d : result)
            {
                if (d.getLineid().equals(lineid))
                {
                    lineStats = d;
                    break;
                }
            }

            if (lineStats == null)
            {
                lineStats = new Topdefectsdto();
                lineStats.setLineid(lineid);
                lineStats.setEventcount(0);
                lineStats.setTotaldefects(0);
                result.add(lineStats);
            }

            //  counts update
            lineStats.setEventcount(lineStats.getEventcount() + 1);
            if (e.getDefectcount() != -1)
            {
                lineStats.setTotaldefects(lineStats.getTotaldefects() + e.getDefectcount());
            }
        }

        //  calculate the percent
        for ( Topdefectsdto d : result)
        {
            double percent;
            if (d.getEventcount() == 0)
            {
                percent = 0;
            }
            else
            {
                percent = ((double) d.getTotaldefects() / d.getEventcount()) * 100;
            }

            // round to 2 decimals
            percent = Math.round(percent * 100.0) / 100.0;
            d.setDefectspercent(percent);
        }

        //  sort by total defects
        for (int i = 0; i < result.size(); i++)
        {
            for (int j = i + 1; j < result.size(); j++)
            {
                if (result.get(j).getTotaldefects() > result.get(i).getTotaldefects())
                {

                    Topdefectsdto temp = result.get(i);
                    result.set(i, result.get(j));
                    result.set(j, temp);
                }
            }
        }

        //limit
        if (result.size() > limit)
        {
            result = result.subList(0, limit);
        }

        return result;
    }

}
