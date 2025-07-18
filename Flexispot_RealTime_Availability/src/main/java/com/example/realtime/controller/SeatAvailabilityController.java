package com.example.realtime.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.realtime.model.SeatAvailablity;
import com.example.realtime.service.SeatAvailabilityScheduler;
import com.example.realtime.service.SeatAvailabilityService;

@Controller
@RestController
@CrossOrigin(origins = "http://localhost:3000/") 
@RequestMapping("/seat")
public class SeatAvailabilityController {

	@Autowired
	private SeatAvailabilityService service;
	
	@Autowired
	private SeatAvailabilityScheduler serviceScheduler;
	
	@GetMapping("/availability")
	public List<SeatAvailablity> getSeatAvailability() {
		serviceScheduler.autoReleaseExpiredSeats();
//	    return service.getAllSeats();
		return service.getAllSeatsRaw();
	}
	
	@PostMapping("/create")
    public ResponseEntity<SeatAvailablity> createSeat(@RequestBody SeatAvailablity seat) {
        SeatAvailablity savedSeat = service.saveSlot(seat);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSeat);
    }
	
	
	  @PutMapping("/cancel/{id}")
	    public ResponseEntity<String> cancelSeat(@PathVariable Integer id) {
	        boolean result = service.cancelSeat(id);
	        if (result) {
	            return ResponseEntity.ok("Seat cancelled successfully");
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
		
	
}
