package com.example.realtime.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.realtime.model.SeatAvailablity;
import com.example.realtime.repository.SeatAvailabilityRepo;

@Service
public class SeatAvailabilityService {

	@Autowired
	private SeatAvailabilityRepo repo;
	
	public List<SeatAvailablity> getAllSeats() {
	    return repo.findByIsAvailableTrue();
	    
	}
	
	 public SeatAvailablity saveSlot(SeatAvailablity seat) {
//		 if (seat.isAvailable() && seat.getAvailableSince() == null) {
//	            seat.setAvailableSince(LocalDateTime.now());
//	        }
//	        return repo.save(seat);
		 
		 Optional<SeatAvailablity> existingSeat = repo.findBySeatId(seat.getSeatId());

		    if (existingSeat.isPresent()) {
		        SeatAvailablity existing = existingSeat.get();
		        existing.setAvailable(seat.isAvailable());
		        existing.setLocation(seat.getLocation());
		        existing.setTimeSlot(seat.getTimeSlot());
		        existing.setDurationMinutes(seat.getDurationMinutes());

		        if (seat.isAvailable()) {
		            existing.setAvailableSince(LocalDateTime.now());
		        }

		        return repo.save(existing);
		    } else {
		        // New seat – insert
		        if (seat.isAvailable()) {
		            seat.setAvailableSince(LocalDateTime.now());
		        }
		        return repo.save(seat);
		    }
		 
		 
	    }
	 
	 public List<SeatAvailablity> getAllSeatsRaw() {
	        return repo.findAll();
	    }
	 
	 public Optional<SeatAvailablity> getBySeatId(String seatId) {
	        return repo.findBySeatId(seatId);
	    }
	 
	 
	 public boolean cancelSeat(String seatId) {
	        Optional<SeatAvailablity> optional = repo.findBySeatId(seatId);
	        if (optional.isPresent()) {
	            SeatAvailablity seat = optional.get();
	            seat.setAvailable(true);
	            seat.setAvailableSince(LocalDateTime.now());
	            seat.setTimeSlot(null); // Optional: clear old timeSlot
	            repo.save(seat);
	            return true;
	        }
	        return false;
	    }
}
