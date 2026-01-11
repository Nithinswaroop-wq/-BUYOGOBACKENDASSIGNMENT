package com.example.BuyogoBackendAssignment.Service;

import com.example.BuyogoBackendAssignment.DTO.Eventsdto;
import com.example.BuyogoBackendAssignment.DTO.Rejectiondto;
import com.example.BuyogoBackendAssignment.DTO.Responsedto;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class Eventsservice
{
    ConcurrentHashMap<String, Eventsdto> largeMap = new ConcurrentHashMap<>();

    public Responsedto eventservicelogic(List<Eventsdto> eventsdto)
   {
       Responsedto response=new Responsedto();

       Instant now=Instant.now();
       for (Eventsdto dto:eventsdto)
       {
           if (dto.getDurationms()<0 || dto.getDurationms()> Duration.ofHours(6).toMillis())
           {
               response.setRejected(response.getRejected()+1);//increment rejectedcount in Responsedto
               Rejectiondto rejection=new Rejectiondto();
               rejection.setEventid(dto.getEventid());
               rejection.setReason("invalid duration");
               response.getRejections().add(rejection);
               //if rejection happens i have to send that object as response also with message
               continue;
           }
           else if (dto.getEventtime().isAfter(now.plus(Duration.ofMinutes(15))))
           {
               response.setRejected(response.getRejected()+1);//increment rejectedcount in Responsedto
               Rejectiondto rejection=new Rejectiondto();
               rejection.setEventid(dto.getEventid());
               rejection.setReason("future event time");
               response.getRejections().add(rejection);//if rejection happens i have to send that object as response also with message

               continue;
           }
           else if (largeMap.containsKey(dto.getEventid())) {
               //compare payload
               Eventsdto payload = largeMap.get(dto.getEventid());
               if (payload.getEventtime().equals(dto.getEventtime()) && payload.getMachineid().equals(dto.getMachineid()) && (payload.getDurationms() == dto.getDurationms()) && (payload.getDefectcount() == dto.getDefectcount()))//payload is same
               {
                   //ignore it
                   response.setDeduped(response.getDeduped() + 1);
               } else//payload is different
               {
                   largeMap.put(dto.getEventid(), dto);//update the payload
                   response.setUpdated(response.getUpdated() + 1); //increment updated variable
               }
               continue;
           }
           else
           {
               dto.setReceivedtime(now);
               largeMap.put(dto.getEventid(), dto);
               response.setAccepted(response.getAccepted()+1);
           }


       }
       return response;
   }
    public ConcurrentHashMap<String, Eventsdto> getStore() {
        return largeMap;
    }


}
