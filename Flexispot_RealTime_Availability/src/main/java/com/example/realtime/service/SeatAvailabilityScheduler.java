package com.example.realtime.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.realtime.model.SeatAvailablity;
import com.example.realtime.repository.SeatAvailabilityRepo;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

@Service
public class SeatAvailabilityScheduler {

	@Autowired
	private SeatAvailabilityRepo repo;
	
	
	
	
	
	@Scheduled(fixedRate = 60000) // runs every 1 minute
    public void autoReleaseExpiredSeats() {
        LocalDateTime now = LocalDateTime.now();

        List<SeatAvailablity> allSeats = repo.findAll();
        for (SeatAvailablity seat : allSeats) {
            if (!seat.isAvailable()
                    && seat.getTimeSlot() != null
                    && seat.getDurationMinutes() != null
                    && seat.getTimeSlot().plusMinutes(seat.getDurationMinutes()).isBefore(now)) {
                seat.setAvailable(true);
                
                seat.setAvailableSince(LocalDateTime.now());
                repo.save(seat);
            }
        }
}
}
