package com.example.realtime.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.realtime.model.SeatAvailablity;
import com.example.realtime.repository.SeatAvailabilityRepo;

@Service
public class SeatAvailabilityScheduler {

	@Autowired
	private SeatAvailabilityRepo repo;
	
	
	
	@Scheduled(fixedRate = 60000) // runs every 1 minute
    public void autoReleaseExpiredSeats() {
        LocalDateTime now = LocalDateTime.now();

        List<SeatAvailablity> allSeats = repo.findAll();
        for (SeatAvailablity seat : allSeats) {
            if (!seat.isAvailable() && seat.getTimeSlot().plusHours(1).isBefore(now)) {
                seat.setAvailable(true);
                repo.save(seat);
            }
        }
}
}
