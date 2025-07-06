package com.example.realtime.service;

import java.util.List;

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
	        return repo.save(seat);
	    }
}
