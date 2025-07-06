package com.example.realtime.model;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SeatAvailablity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	 
	private String seatId;
	    private boolean isAvailable;
	    private String location;
	    private LocalDateTime timeSlot;
	    
	    
	    public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getSeatId() {
			return seatId;
		}
		public void setSeatId(String seatId) {
			this.seatId = seatId;
		}
		public boolean isAvailable() {
			return isAvailable;
		}
		public void setAvailable(boolean isAvailable) {
			this.isAvailable = isAvailable;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public LocalDateTime getTimeSlot() {
			return timeSlot;
		}
		public void setTimeSlot(LocalDateTime timeSlot) {
			this.timeSlot = timeSlot;
		}
		
		
		
		public SeatAvailablity() {
			
		}
		public SeatAvailablity(Integer id,String seatId, boolean isAvailable, String location, LocalDateTime timeSlot) {
			super();
			this.id=id;
			this.seatId = seatId;
			this.isAvailable = isAvailable;
			this.location = location;
			this.timeSlot = timeSlot;
		}
		
	    
}
